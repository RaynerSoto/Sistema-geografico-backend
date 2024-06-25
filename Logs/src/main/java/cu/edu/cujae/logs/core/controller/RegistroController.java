package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.mapping.Estado;
import cu.edu.cujae.logs.core.mapping.Registro;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.services.EstadoService;
import cu.edu.cujae.logs.core.services.RegistroService;
import cu.edu.cujae.logs.core.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/registro")
public class RegistroController {
    @Autowired
    private RegistroService registroService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EstadoService estadoService;

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

}
