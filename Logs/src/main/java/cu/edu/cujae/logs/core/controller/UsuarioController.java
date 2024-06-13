package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.clases.Usuario;
import cu.edu.cujae.logs.core.services.UsuarioService;
import cu.edu.cujae.logs.core.servicesInterfaces.UsuarioServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServiceInterfaces usuarioService;

    @GetMapping("/")
    public ResponseEntity<?> listarUsuarios() {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuarios());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<String> insertarUsuario(Usuario usuario) {
        try {
            usuarioService.insertarUsuario(usuario);
            return ResponseEntity.ok().body("Usuario insertado correctamente");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<String> actualizarUsuario(Usuario usuario, Long id) {
        try {
            usuarioService.modificarUsuario(usuario,id);
            return ResponseEntity.ok().body("Usuario modificado correctamente");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public ResponseEntity<String> eliminarUsuario(Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok().body("Usuario eliminado correctamente");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
