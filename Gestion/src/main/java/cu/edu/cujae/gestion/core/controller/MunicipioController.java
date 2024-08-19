package cu.edu.cujae.gestion.core.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.edu.cujae.gestion.core.dto.MunicipioDto;
import cu.edu.cujae.gestion.core.dto.TokenDto;
import cu.edu.cujae.gestion.core.dto.UsuarioDto;
import cu.edu.cujae.gestion.core.feignclient.TokenServiceInterfaces;
import cu.edu.cujae.gestion.core.libs.RegistroUtils;
import cu.edu.cujae.gestion.core.libs.TokenUtils;
import cu.edu.cujae.gestion.core.servicesInterfaces.MunicipioServicesInterfaces;
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
@RequestMapping("/api/v1/gestion/municipio")
@Tag(name = "Controlador de municipios",
        description = "Permite hacer todas las operaciones de los municipios")
@SecurityRequirement(name = "bearer-key")
public class MunicipioController {

    private final MunicipioServicesInterfaces municipioServices;
    private final RegistroUtils registroUtils;
    private final TokenServiceInterfaces tokenService;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public MunicipioController(MunicipioServicesInterfaces municipioServices, RegistroUtils registroUtils, TokenServiceInterfaces tokenService) {
        this.municipioServices = municipioServices;
        this.registroUtils = registroUtils;
        this.tokenService = tokenService;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de municipios",
    description = "Permite obtener el listado de todos los municipios del sistema",security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador','Gestor')")
    public ResponseEntity<?> getAllMunicipios(HttpServletRequest request) {
        String actividad = "Listar todos todos los municipios del sistema";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            List<MunicipioDto> municipioDtos = municipioServices.listadoMunicipios().stream()
                    .map(MunicipioDto::new)
                    .sorted(Comparator.comparing(MunicipioDto::getUuid))
                    .toList();
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok(municipioDtos);
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/Provincia")
    @Operation(summary = "Listado de municipios por provincia",
            description = "Permite obtener el listado de todos los municipios pertenecientes a una provincia",security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador','Gestor')")
    public ResponseEntity<?> getAllMunicipiosXProvincia(@PathVariable String provincia, HttpServletRequest request) {
        String actividad = "Listar todos los municipios pertenecientes a una provincia";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            List<MunicipioDto> municipioDtos = municipioServices.listadoMunicipios().stream()
                    .map(MunicipioDto::new)
                    .filter(municipioDto -> municipioDto.getProvincia().equalsIgnoreCase(provincia))
                    .sorted(Comparator.comparing(MunicipioDto::getUuid))
                    .toList();
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok(municipioDtos);
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
