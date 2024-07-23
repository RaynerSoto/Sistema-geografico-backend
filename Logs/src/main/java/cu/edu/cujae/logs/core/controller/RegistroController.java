package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.mapping.Estado;
import cu.edu.cujae.logs.core.mapping.Registro;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.services.EstadoService;
import cu.edu.cujae.logs.core.services.RegistroService;
import cu.edu.cujae.logs.core.services.UsuarioService;
import cu.edu.cujae.logs.core.servicesInterfaces.EstadoServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.RegistroServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.UsuarioServiceInterfaces;
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

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/registro")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controlador del registro",description = "Determina el registro de logs del usuario")
public class RegistroController {
    @Autowired
    private RegistroServiceInterfaces registroService;
    @Autowired
    private UsuarioServiceInterfaces usuarioService;
    @Autowired
    private EstadoServiceInterfaces estadoService;
    @Autowired
    private RegistroUtils registroUtils;
    @Autowired
    private TokenUtils tokenUtils;

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador','Gestor')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Registra la actividad del usuario")
    //@PostMapping("/")
    @Deprecated
    public ResponseEntity<?> registro(@RequestBody RegistroDto registro,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los usuarios");
        try {
            Optional<Usuario> usuario = usuarioService.buscarUsuarioPorUsernameActivo(registro.getUsuario());
            Optional<Estado> estado = estadoService.obtenerEstado(registro.getEstado());
            Registro regis = new Registro(registro, usuario.get(), estado.get());
            registroService.insertarRegistro(regis);
            return ResponseEntity.ok("Registro creado correctamente");
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }


    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite ver el listado de actividades de todos los usuarios")
    @GetMapping("/")
    public ResponseEntity<?> listarRegistros(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los registros");
        try {
            List<RegistroDto> registroDtoList = registroService.listadoRegistros();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok(registroDtoList);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e);
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite ver el listado de actividades de usuario")
    @GetMapping("/{id}")
    public ResponseEntity<?> listarRegistros(@PathVariable Long id, HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Listado de actividades de un usuario");
        try {
            List<Registro> registros = registroService.listarRegistros();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok(registros);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e);
        }
    }
}
