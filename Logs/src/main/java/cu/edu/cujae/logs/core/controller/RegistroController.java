package cu.edu.cujae.logs.core.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.mapper.Estado;
import cu.edu.cujae.logs.core.mapper.Registro;
import cu.edu.cujae.logs.core.mapper.Usuario;
import cu.edu.cujae.logs.core.security.TokenService;
import cu.edu.cujae.logs.core.services.EstadoServiceInterfaces;
import cu.edu.cujae.logs.core.services.RegistroServiceInterfaces;
import cu.edu.cujae.logs.core.services.UsuarioServiceInterfaces;
import cu.edu.cujae.logs.core.utils.RegistroUtils;
import cu.edu.cujae.logs.core.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/login/registro")
//@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controlador del registro",description = "Determina el registro de logs del usuario")
public class RegistroController {

    private final RegistroServiceInterfaces registroService;
    private final UsuarioServiceInterfaces usuarioService;
    private final EstadoServiceInterfaces estadoService;
    private final RegistroUtils registroUtils;
    private final TokenUtils tokenUtils;
    private final TokenService tokenService;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public RegistroController(RegistroServiceInterfaces registroService, UsuarioServiceInterfaces usuarioService, EstadoServiceInterfaces estadoService, RegistroUtils registroUtils, TokenUtils tokenUtils,TokenService tokenService) {
        this.tokenService = tokenService;
        this.registroService = registroService;
        this.usuarioService = usuarioService;
        this.estadoService = estadoService;
        this.registroUtils = registroUtils;
        this.tokenUtils = tokenUtils;
    }

    //@PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador','Gestor','null')")
    @Operation(summary = "Registra la actividad del usuario")
    @PostMapping("/")
    public ResponseEntity<?> registro(@RequestBody RegistroDto registro,@RequestHeader String username) {
        try {
            if ((username.isBlank() || username.isEmpty() || username.equals(null)) == false){
                try {
                    if(username.equals(registro.getUsuario()) == false)
                        throw new Exception("No es posible registrar la actividad");
                    Optional<Usuario> usuario = usuarioService.buscarUsuarioPorUsernameActivo(registro.getUsuario());
                    Optional<Estado> estado = estadoService.obtenerEstado(registro.getEstado());
                    Registro regis = new Registro(registro, usuario.get(), estado.get());
                    registroService.insertarRegistro(regis);
                    return ResponseEntity.ok("Registro creado correctamente");
                }
                catch (Exception e) {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
            }else{
                return ResponseEntity.badRequest().body("No se encuentra autenticaciòn");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite ver el listado de actividades de todos los usuarios")
    @GetMapping("/")
    public ResponseEntity<?> listarRegistros(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los registros");
        try {
            List<RegistroDto> registroDtoList = registroService.listadoRegistros();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok(registroDtoList);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se puede listar los registros del sistema. Mensaje de error: "+ e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite ver el listado de actividades de usuario")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarRegistros(@PathVariable Long id, HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Listado de actividades de un usuario");
        try {
            List<Registro> registros = registroService.listarRegistros();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok(registros);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se puede aaceeder al registro del usuario. Mensaje de error: " + e.getMessage());
        }
    }
}
