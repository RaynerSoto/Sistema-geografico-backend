package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.UsuarioDto;
import cu.edu.cujae.logs.core.mapping.Rol;
import cu.edu.cujae.logs.core.mapping.Sexo;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.repository.RolRepository;
import cu.edu.cujae.logs.core.services.RolService;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.SexoServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.UsuarioServiceInterfaces;
import cu.edu.cujae.logs.core.utils.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServiceInterfaces usuarioService;
    @Autowired
    private RolServiceInterfaces rolRepository;
    @Autowired
    private SexoServiceInterfaces sexoService;

    @GetMapping
    public ResponseEntity<?> listarAllUsuarios() {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuarios().stream().map(
                    s -> new UsuarioDto(s)).toList()
            );
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/userActivos")
    public ResponseEntity<?> listarUsuariosActivos() {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuarios().stream().filter(s-> s.isActivo())
                    .map(s-> new UsuarioDto(s)).toList());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/userEliminado")
    public ResponseEntity<?> listarUsuariosEliminados() {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuarios().stream().filter(s-> !s.isActivo())
                    .map(s-> new UsuarioDto(s)).toList());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/")
    public ResponseEntity<String> insertarUsuario(@RequestBody UsuarioDto usuario) {
        String resultado = Validacion.comprobacionValidador(usuario);
        try {
            if (resultado.isEmpty() || resultado.isBlank()){
                Optional<Rol> rol = rolRepository.consultarRolNombre(usuario.getRol());
                Optional<Sexo> sexo = sexoService.consultarSexo(usuario.getSexo());
                usuarioService.validarUsuarioInsertar(usuario.getEmail(),usuario.getUsername());
                if (rol.isPresent() && sexo.isPresent() && usuario.isActivo() == true) {
                    usuarioService.insertarUsuario(new Usuario(usuario,rol.get(),sexo.get()));
                    return ResponseEntity.ok().body("Usuario insertado correctamente");
                }else {
                    return ResponseEntity.badRequest().body("Usuario no insertado");
                }
            }
            else {
                return ResponseEntity.badRequest().body(resultado);
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuarioDto usuario,@PathVariable Long id) {
        try {
            String resultado = Validacion.comprobacionValidador(usuario);
            usuario.setUuid(id);
            if (resultado.isEmpty() || resultado.isBlank()){
                Optional<Rol> rol = rolRepository.consultarRolNombre(usuario.getRol());
                Optional<Sexo> sexo = sexoService.consultarSexo(usuario.getSexo());
                usuarioService.validarUsuarioModificar(usuario.getEmail(),usuario.getUsername(),usuario.getUuid());
                if (rol.isPresent() && sexo.isPresent() && usuario.isActivo() == true) {
                    Usuario user = new Usuario(usuario,rol.get(),sexo.get());
                    usuarioService.modificarUsuario(new Usuario(usuario, rol.get(), sexo.get()));
                    return ResponseEntity.ok().body("Usuario modificado correctamente");
                }
                else {
                    return ResponseEntity.badRequest().body("Usuario no modificado");
                }
            }
            else {
                return ResponseEntity.badRequest().body(resultado);
            }
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok().body("Usuario eliminado correctamente");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/isUserActivo")
    public ResponseEntity<String> isUsuarioActivo(UsuarioDto usuario){
        try {
            if (usuarioService.usuarioActivo(usuario.getEmail(), usuario.getUsername()).isPresent()) {
                return ResponseEntity.ok().body("El usuario está activo");
            }
            return ResponseEntity.ok("No debería está aquí");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
