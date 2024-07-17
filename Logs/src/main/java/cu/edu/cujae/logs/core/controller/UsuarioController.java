package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.UsuarioDto;
import cu.edu.cujae.logs.core.dto.UsuarioDtoInsert;
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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuario")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controllador de los usuarios del sistema", description = "Controlla los usuarios registrados en el sistema")
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
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Listado de todos los usuarios independientemente de su estado")
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
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Listados de usuarios activos, devueltos en una página para facilitar al Frontend")
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
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
    summary = "Listado de usuarios activos")
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
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
    summary = "Listados de usuarios no Super Administradores")
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
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Pagína para el frontend de los usuarios eliminados")
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
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Listado de usuarios eliminados")
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
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite insertar un usuario")
    @PostMapping("/")
    public ResponseEntity<String> insertarUsuario(@RequestBody UsuarioDtoInsert usuario) {
        try {
            usuario.setActivo(true);
            Validacion.validarUnsupportedOperationException(usuario);
            Optional<Rol> rol = rolRepository.consultarRol(usuario.getRol());
            Optional<Sexo> sexo = sexoService.consultarSexo(usuario.getSexo());
            usuarioService.validarUsuarioInsertar(usuario.getEmail(),usuario.getUsername());
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            if (usuario.isReactivado() == true){
                Usuario user = usuarioService.obtenerUsuarioEmailUsernameName(usuario.getEmail(),usuario.getUsername(),usuario.getName(),sexo.get()).get();
                user.setFechaEliminacion(null);
                user.setActivo(true);
                user.setFechaCreacion(Timestamp.valueOf(LocalDateTime.now()));
                usuarioService.modificarUsuario(user);
                return ResponseEntity.ok().body("Usuario reactivado correctamente");
            }
            usuarioService.insertarUsuario(new Usuario(usuario,rol.get(),sexo.get()));
            return ResponseEntity.ok().body("Usuario insertado correctamente");
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite actualizar un usuario")
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
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite eliminar un usuario")
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
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite conocer si un usuario está activo o no")
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
