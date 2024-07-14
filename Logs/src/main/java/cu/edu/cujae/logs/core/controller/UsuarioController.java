package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.UsuarioDto;
import cu.edu.cujae.logs.core.exception.GoodException;
import cu.edu.cujae.logs.core.mapping.Rol;
import cu.edu.cujae.logs.core.mapping.Sexo;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.SexoServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.UsuarioServiceInterfaces;
import cu.edu.cujae.logs.core.utils.Validacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping("/")
    public ResponseEntity<?> listarAllUsuarios() {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuarios().stream().map(UsuarioDto::new).toList()
            );
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping("/userActivosPagina")
    public ResponseEntity<?> listarUsuariosActivosPagina(Pageable pageable) {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuarios(pageable).filter(s-> s.isActivo()).map(UsuarioDto::new).toList());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping("/userActivos")
    public ResponseEntity<?> listarUsuariosActivos() {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuarios().stream().filter(s-> s.isActivo())
                    .map(UsuarioDto::new).toList());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping("/userActivosNoSuperAdministrador")
    public ResponseEntity<?> listarUsuariosActivosNoAdministrador() {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuariosNoSuperAdministrador().stream().filter(s-> s.isActivo())
                    .map(UsuarioDto::new).toList());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping("/userEliminadoPagina")
    public ResponseEntity<?> listarUsuariosEliminadosPagina(Pageable pageable) {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuarios(pageable).filter(s-> !s.isActivo())
                    .map(UsuarioDto::new).toList());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @GetMapping("/userEliminado")
    public ResponseEntity<?> listarUsuariosEliminados() {
        try {
            return ResponseEntity.ok().body(usuarioService.listarUsuarios().stream().filter(s-> !s.isActivo())
                    .map(UsuarioDto::new).toList());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PostMapping("/")
    public ResponseEntity<String> insertarUsuario(@RequestBody UsuarioDto usuario) {
        try {
            usuario.setActivo(true);
            Validacion.validarUnsupportedOperationException(usuario);
            Optional<Rol> rol = rolRepository.consultarRol(usuario.getRol());
            Optional<Sexo> sexo = sexoService.consultarSexo(usuario.getSexo());
            usuarioService.validarUsuarioInsertar(usuario.getEmail(),usuario.getUsername());
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioService.insertarUsuario(new Usuario(usuario,rol.get(),sexo.get()));
            return ResponseEntity.ok().body("Usuario insertado correctamente");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuarioDto usuario,@PathVariable Long id) {
        try {
            Validacion.validarUnsupportedOperationException(usuario);
            usuario.setUuid(id);
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
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
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("Usuario eliminado");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
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
