package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.repository.RolRepository;
import cu.edu.cujae.logs.core.repository.SexoRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.SexoServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.UsuarioServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServiceInterfaces usuarioService;
    @Autowired
    private RolServiceInterfaces rolRepository;
    @Autowired
    private SexoServiceInterfaces sexoService;
    @Autowired
    private RolRepository repositoryRol;
    @Autowired
    private SexoRepository repositorySexo;

    @GetMapping("/")
    public ResponseEntity<?> listarUsuarios() {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuarios());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /*
    @PostMapping("/")
    public ResponseEntity<String> insertarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario user = new Usuario(usuario);
            Optional<Rol> rol = rolRepository.consultarRolID(usuario.getRolIDLong());
            Optional<Sexo> sexo = sexoService.buscarSexoPorId(usuario.getSexoIDLong());
            if (rol.isPresent() && sexo.isPresent()) {
                user.setRol(rol.get());
                user.setSexo(sexo.get());
                usuarioService.insertarUsuario(user);
                return ResponseEntity.ok().body("Usuario insertado correctamente");
            }else {
                return ResponseEntity.badRequest().body("Usuario no insertado");
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }*/
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
