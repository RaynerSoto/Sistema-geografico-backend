package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.mapping.Estado;
import cu.edu.cujae.logs.core.mapping.Registro;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.services.EstadoService;
import cu.edu.cujae.logs.core.services.RegistroService;
import cu.edu.cujae.logs.core.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/registro")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controlador del registro",description = "Determina el registro de logs del usuario")
public class RegistroController {
    @Autowired
    private RegistroService registroService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EstadoService estadoService;

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador','Gestor')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Registra la actividad del usuario")
    @PostMapping("/")
    public ResponseEntity<?> registro(@RequestBody RegistroDto registro) {
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
    public ResponseEntity<?> listarRegistros() {
        try {
            return ResponseEntity.ok(registroService.listarRegistros());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }
}
