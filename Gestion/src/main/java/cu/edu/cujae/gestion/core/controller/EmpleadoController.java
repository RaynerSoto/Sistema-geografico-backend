package cu.edu.cujae.gestion.core.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.edu.cujae.gestion.core.dto.TokenDto;
import cu.edu.cujae.gestion.core.dto.UsuarioDto;
import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDto;
import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDtoInsert;
import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDtoRegular;
import cu.edu.cujae.gestion.core.feignclient.TokenServiceInterfaces;
import cu.edu.cujae.gestion.core.libs.RegistroUtils;
import cu.edu.cujae.gestion.core.libs.TokenUtils;
import cu.edu.cujae.gestion.core.mapping.Empleado;
import cu.edu.cujae.gestion.core.mapping.Entidad;
import cu.edu.cujae.gestion.core.mapping.Municipio;
import cu.edu.cujae.gestion.core.mapping.Provincia;
import cu.edu.cujae.gestion.core.services.RegistroService;
import cu.edu.cujae.gestion.core.servicesInterfaces.EmpleadoServiceInterfaces;
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
@RequestMapping("/api/v1/gestion/empleado")
@Tag(name = "Controlador de empleados",
        description = "Controlador encargardo de todo lo referente con los empleados del sistema")
public class EmpleadoController {

    private final EmpleadoServiceInterfaces empleadoService;
    private final MunicipioServicesInterfaces municipioService;
    private final ProvinciaServiceInterfaces provinciaService;
    private final EntidadServicesInterfaces entidadService;
    private final RegistroService registroService;
    private final RegistroUtils registroUtils;
    private final TokenServiceInterfaces tokenService;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public EmpleadoController(EmpleadoServiceInterfaces empleadoService, MunicipioServicesInterfaces municipioService, ProvinciaServiceInterfaces provinciaService, EntidadServicesInterfaces entidadService, RegistroService registroService, RegistroUtils registroUtils, TokenServiceInterfaces tokenService) {
        this.empleadoService = empleadoService;
        this.municipioService = municipioService;
        this.provinciaService = provinciaService;
        this.entidadService = entidadService;
        this.registroService = registroService;
        this.registroUtils = registroUtils;
        this.tokenService = tokenService;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de empleados"
            ,description = "Permite listar todos los empleados del sistema junto con sus centros laborales")
    public ResponseEntity<?> listarEmpleados(HttpServletRequest request){
        String actividad = "Listado de empleados del sistema junto con sus centros laborales";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            List<EmpleadoDtoRegular> empleados = empleadoService.obtenerEmpleados().stream()
                    .map(empleado -> new EmpleadoDtoRegular(empleado)).toList();
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok(empleados);
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/")
    @Operation(summary = "Insertar empleado sin trabajo",
    description = "Permite insertar un empleado que no está afiliado a un centro laboral")
    public ResponseEntity<?> insertarEmpleadoSinCompañia(@RequestBody EmpleadoDto empleadoDto,HttpServletRequest request){
        String actividad = "Insertar un empleado que no está afiliado a un centro laboral";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            Validacion.validarUnsupportedOperationException(empleadoDto);
            Optional<Provincia> provincia = provinciaService.buscarProvinciaPorNombre(empleadoDto.getProvincia());
            Optional<Municipio> municipio = municipioService.obtenerMunicipioNombre(empleadoDto.getMunicipio());
            if (!municipioService.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
                return ResponseEntity.badRequest().body("Este municipio no pertenece a la provincia");
            empleadoService.insertarEmpleado(new Empleado(empleadoDto,municipio.get(),provincia.get()));
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok("Usuario insertado con éxito");
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/compannia")
    @Operation(summary = "Insertar empleado con trabajo",
    description = "Permite insertar un empleado que está afiliado a un centro laboral")
    public ResponseEntity<?> insertarEmpleadoConCompañia(@RequestBody EmpleadoDtoInsert empleadoDto,HttpServletRequest request){
        String actividad = "Insertar un empleado que está afiliado a un centro laboral";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            Validacion.validarUnsupportedOperationException(empleadoDto);
            Optional<Entidad> entidad = entidadService.obtenerEntidadNombre(empleadoDto.getEntidad());
            Optional<Provincia> provincia = provinciaService.buscarProvinciaPorNombre(empleadoDto.getProvincia());
            Optional<Municipio> municipio = municipioService.obtenerMunicipioNombre(empleadoDto.getMunicipio());
            if (!municipioService.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
                return ResponseEntity.badRequest().body("Este municipio no pertenece a la provincia");
            entidad.get().getPersonal().add(new Empleado(empleadoDto,municipio.get(),provincia.get()));
            entidadService.modificarEntidad(entidad.get());
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok("Usuario insertado con éxito");
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{ci}")
    @Operation(summary = "Obtener empleado por CI",
    description = "Permite obtener un empleado a raíz de su carnet de identidad")
    public ResponseEntity<?> obtenerEmpleadosPorCi(@PathVariable String ci,HttpServletRequest request){
        String actividad = "Obtener un empleado a raìz del CI";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            EmpleadoDtoRegular empleado = new EmpleadoDtoRegular(empleadoService.obtenerEmpleadoXCi(ci).get());
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok(empleado);
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado por ID",
            description = "Permite obtener un empleado a raíz de su ID")
    public ResponseEntity<?> obtenerEmpleadosPorId(@PathVariable Long id,HttpServletRequest request){
        String actividad = "Obtener un empleado a raìz de su ID";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            EmpleadoDtoRegular empleado = new EmpleadoDtoRegular(empleadoService.obtenerEmpleadoXId(id).get());
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok(empleado);
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar empleado por ID",
            description = "Permite modificar un empleado a raíz de su ID")
    public ResponseEntity<?> modificarEmpleadoXId(@PathVariable Long id,@RequestBody EmpleadoDto empleadoDto,HttpServletRequest request){
        String actividad = "Modificar un empleado a raìz de su ID";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            Validacion.validarUnsupportedOperationException(empleadoDto);
            empleadoDto.setUuid(id);
            Optional<Provincia> provincia = provinciaService.buscarProvinciaPorNombre(empleadoDto.getProvincia());
            Optional<Municipio> municipio = municipioService.obtenerMunicipioNombre(empleadoDto.getMunicipio());
            if (!municipioService.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
                return ResponseEntity.badRequest().body("Este municipio no pertenece a la provincia");
            empleadoService.modificarEmpleado(new Empleado(empleadoDto,municipio.get(),provincia.get()));
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok("Usuario insertado con éxito");
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empleado por ID",
            description = "Permite eliminar un empleado a raíz de su ID")
    public ResponseEntity<?> eliminarEmpleadoXId(@PathVariable Long id,HttpServletRequest request){
        String actividad = "Eliminar un empleado a raíz de su ID";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            empleadoService.eliminarEmpleado(id);
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok("Empleado eliminado");
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{ci}")
    @Operation(summary = "Eliminar empleado por CI",
            description = "Permite eliminar un empleado a raíz de su carnet de identidad")
    public ResponseEntity<?> eliminarEmpleadoXCi(@PathVariable String ci,HttpServletRequest request){
        String actividad = "Eliminar un empleado a raìz de su carnet de identidad";
        TokenDto tokenDto = TokenUtils.getTokenDto(request);
        try {
            empleadoService.eliminarEmpleado(ci);
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Aceptado");
            return ResponseEntity.ok("Empleado eliminado");
        }catch (Exception e){
            registroUtils.insertarRegistro(mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class).getUsername(),actividad,request.getRemoteHost(),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
