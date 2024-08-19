package cu.edu.cujae.gestion.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cu.edu.cujae.gestion.core.dto.EntidadDto;
import cu.edu.cujae.gestion.core.dto.TokenDto;
import cu.edu.cujae.gestion.core.dto.UsuarioDto;
import cu.edu.cujae.gestion.core.feignclient.TokenServiceInterfaces;
import cu.edu.cujae.gestion.core.libs.RegistroUtils;
import cu.edu.cujae.gestion.core.libs.TokenUtils;
import cu.edu.cujae.gestion.core.mapping.Entidad;
import cu.edu.cujae.gestion.core.mapping.Municipio;
import cu.edu.cujae.gestion.core.mapping.Provincia;
import cu.edu.cujae.gestion.core.services.RegistroService;
import cu.edu.cujae.gestion.core.servicesInterfaces.EntidadServicesInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.MunicipioServicesInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.ProvinciaServiceInterfaces;
import cu.edu.cujae.gestion.core.libs.Validacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/gestion/entidad",name = "Controller para las entidades")
@Tag(name = "Controllador de la entidad"
        ,description = "Es el responsable de controllar todo lo referente con las entidades del sistema")
public class EntidadController {

    private final EntidadServicesInterfaces entidadServices;
    private final ProvinciaServiceInterfaces provinciaServices;
    private final MunicipioServicesInterfaces municipioServices;
    private final RegistroService registroService;
    private final RegistroUtils registroUtils;
    private final TokenServiceInterfaces tokenService;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public EntidadController(EntidadServicesInterfaces entidadServices, ProvinciaServiceInterfaces provinciaServices, MunicipioServicesInterfaces municipioServices, RegistroService registroService, RegistroUtils registroUtils, TokenServiceInterfaces tokenService) {
        this.entidadServices = entidadServices;
        this.provinciaServices = provinciaServices;
        this.municipioServices = municipioServices;
        this.registroService = registroService;
        this.registroUtils = registroUtils;
        this.tokenService = tokenService;
    }

    @GetMapping("/")
    @Operation(description = "Permite listar todas las entidades del sistema",
    summary = "Listar Entidades")
    public ResponseEntity<?> listadoEntidades(HttpServletRequest request){
        String actividad = "Listar todas las entidades del sistema";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            List<EntidadDto> entidades = entidadServices.listarEntidad().stream().map(EntidadDto::new).toList();
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok(entidades);
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body("Error en cargar el listado de entidades");
        }
    }

    @PostMapping("/")
    @Operation(summary = "Insertar Entidad",
    description = "Permite insertar una entidad")
    public ResponseEntity<?> insertarEntidad(@RequestBody EntidadDto entidad,HttpServletRequest request){
        String actividad = "Insertar una entidad en el sistema";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            entidadServices.existeEntidadNombre(entidad.getNombre());
            Validacion.validarUnsupportedOperationException(entidad);
            Optional<Provincia> provincia = provinciaServices.buscarProvinciaPorNombre(entidad.getProvincia());
            Optional<Municipio> municipio = municipioServices.obtenerMunicipioNombre(entidad.getMunicipio());
            if (!municipioServices.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
                return ResponseEntity.badRequest().body("Este municipio no pertenece a la provincia");
            entidadServices.insertarEntidad(new Entidad(entidad,municipio.get(),provincia.get()));
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok("Entidad insertada");
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar entidad",
    description = "Permite modificar una entidad a través de su ID")
    public ResponseEntity<?> modificarEntidad(@RequestBody EntidadDto entidad,@PathVariable Long id,HttpServletRequest request){
        String actividad = "Modificar una entidad a travès de su ID";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            entidad.setUuid(id);
            entidadServices.existeEntidadNombreNotId(entidad.getNombre(),entidad.getUuid());
            Validacion.validarUnsupportedOperationException(entidad);
            Optional<Provincia> provincia = provinciaServices.buscarProvinciaPorNombre(entidad.getProvincia());
            Optional<Municipio> municipio = municipioServices.obtenerMunicipioNombre(entidad.getMunicipio());
            if (!municipioServices.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
                return ResponseEntity.badRequest().body("Este municipio no pertenece a la provincia");
            entidadServices.modificarEntidad(new Entidad(entidad,municipio.get(),provincia.get()));
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok("Entidad modificada con éxito");
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar entidad",
    description = "Permite eliminar entidades a través de su ID")
    public ResponseEntity<?> eliminarEntidad(@PathVariable Long id,HttpServletRequest request){
        String actividad = "Eliminar una entidad del sistema por su ID";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            entidadServices.eliminarEntidad(id);
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok("Entidad eliminada con éxito");
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
