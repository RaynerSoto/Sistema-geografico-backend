package cu.edu.cujae.gestion.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cu.edu.cujae.gestion.core.dto.ProvinciaDto;
import cu.edu.cujae.gestion.core.dto.TokenDto;
import cu.edu.cujae.gestion.core.dto.UsuarioDto;
import cu.edu.cujae.gestion.core.feignclient.TokenServiceInterfaces;
import cu.edu.cujae.gestion.core.util.IpUtils;
import cu.edu.cujae.gestion.core.util.RegistroUtils;
import cu.edu.cujae.gestion.core.util.TokenUtils;
import cu.edu.cujae.gestion.core.services.servicesImpl.RegistroService;
import cu.edu.cujae.gestion.core.services.ProvinciaServiceInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gestion/provincia")
@Tag(name = "Controllador de provincias",
        description = "Controllador encargado de todo lo relacionado con las provincias del país")
@SecurityRequirement(name = "bearer-key")
public class ProvinciaController {

    private final ProvinciaServiceInterfaces provinciaService;
    private final RegistroService registroService;
    private final RegistroUtils registroUtils;
    private final TokenServiceInterfaces tokenService;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public ProvinciaController(ProvinciaServiceInterfaces provinciaService, RegistroService registroService, RegistroUtils registroUtils, TokenServiceInterfaces tokenService) {
        this.provinciaService = provinciaService;
        this.registroService = registroService;
        this.registroUtils = registroUtils;
        this.tokenService = tokenService;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de provincias",
            description = "Permite obtener el listado de todos las provincias del sistema",security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador','Gestor')")
    public ResponseEntity<?> getAllProvincia(HttpServletRequest request) {
        String actividad = "Obtener listado de todos las provincias del sistema";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            List<ProvinciaDto> pronvincias = provinciaService.listadoProvincia().stream()
                    .map(ProvinciaDto::new)
                    .sorted(Comparator.comparing(ProvinciaDto::getUuid))
                    .toList();
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad, IpUtils.hostIpV4Http(request),"Aceptado");
            return ResponseEntity.ok(pronvincias);
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,IpUtils.hostIpV4Http(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/nombre")
    @Operation(summary = "Obtener provincias por nombre",
            description = "Obtener todos los datos de la provincia según su nombre",security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador','Gestor')")
    public ResponseEntity<?> getProvinciaNombre(@PathVariable String nombre, HttpServletRequest request) {
        String actividad = "Obtener todos los datos de la provincia según su nombre";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            ProvinciaDto provinciaDto = new ProvinciaDto(provinciaService.buscarProvinciaPorNombre(nombre));
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,IpUtils.hostIpV4Http(request),"Aceptado");
            return ResponseEntity.ok(provinciaDto);
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,IpUtils.hostIpV4Http(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
