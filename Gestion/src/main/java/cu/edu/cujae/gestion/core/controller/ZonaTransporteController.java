package cu.edu.cujae.gestion.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cu.edu.cujae.gestion.core.dto.TokenDto;
import cu.edu.cujae.gestion.core.dto.UsuarioDto;
import cu.edu.cujae.gestion.core.dto.ZonaTransporteDto;
import cu.edu.cujae.gestion.core.feignclient.TokenServiceInterfaces;
import cu.edu.cujae.gestion.core.libs.RegistroUtils;
import cu.edu.cujae.gestion.core.libs.TokenUtils;
import cu.edu.cujae.gestion.core.servicesInterfaces.ZonaTransporteServiceInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gestion/zonaTransporte")
@Tag(name = "Controllador de zona de transporte")
@SecurityRequirement(name = "bearer-key")
public class ZonaTransporteController {

    private final ZonaTransporteServiceInterfaces zonaTransporteService;
    private final RegistroUtils registroUtils;
    private final TokenServiceInterfaces tokenService;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public ZonaTransporteController(ZonaTransporteServiceInterfaces zonaTransporteService, RegistroUtils registroUtils, TokenServiceInterfaces tokenService) {
        this.zonaTransporteService = zonaTransporteService;
        this.registroUtils = registroUtils;
        this.tokenService = tokenService;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de zonas de transportes",
            description = "Permite obtener el listado de todas las zonas de transportes del sistema"
    ,security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador','Gestor')")
    public ResponseEntity<?> getZonaTransporte(HttpServletRequest request) {
        String actividad = "Obtener el listado de todas las zonas de transportes del sistema";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            List<ZonaTransporteDto> zonaTransporteDtos =  zonaTransporteService.listadoZonaTransporte().stream()
                    .map(ZonaTransporteDto::new).sorted(Comparator.comparing(ZonaTransporteDto::getUuid))
                    .toList();
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok(zonaTransporteDtos);
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
