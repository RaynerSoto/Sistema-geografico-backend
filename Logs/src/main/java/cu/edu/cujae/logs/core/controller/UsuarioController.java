package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.logs.core.dto.usuario.UsuarioDtoInsert;
import cu.edu.cujae.logs.core.mapping.Rol;
import cu.edu.cujae.logs.core.mapping.Sexo;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.SexoServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.UsuarioServiceInterfaces;
import cu.edu.cujae.logs.core.utils.RegistroUtils;
import cu.edu.cujae.logs.core.utils.TokenUtils;
import cu.edu.cujae.logs.core.utils.Validacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired
    private RegistroUtils registroUtils;
    @Autowired
    private TokenUtils tokenUtils;

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Listado de todos los usuarios independientemente de su estado")
    @GetMapping("/")
    public ResponseEntity<?> listarAllUsuarios(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los usuarios");
        try {
            List<UsuarioDto> user = usuarioService.listarUsuarios().stream().map(UsuarioDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok().body(user);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Listados de usuarios activos, devueltos en una página para facilitar al Frontend")
    @GetMapping("/userActivosPagina")
    public ResponseEntity<?> listarUsuariosActivosPagina(Pageable pageable,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los usuarios como un archivo paginable");
        try {
            List<UsuarioDto> usuarioDtos = usuarioService.listarUsuarios(pageable).filter(s-> s.isActivo()).map(UsuarioDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok().body(usuarioDtos);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
    summary = "Listado de usuarios activos")
    @GetMapping("/userActivos")
    public ResponseEntity<?> listarUsuariosActivos(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los usuarios activos");
        try {
            List<UsuarioDto> usuarioDtos = usuarioService.listarUsuarios().stream().filter(s-> s.isActivo())
                    .map(UsuarioDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok().body(usuarioDtos);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
    summary = "Listados de usuarios no Super Administradores")
    @GetMapping("/userActivosNoSuperAdministrador")
    public ResponseEntity<?> listarUsuariosActivosNoAdministrador(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los usuarios no administrador");
        try {
            List<UsuarioDto> usuarioDtos = usuarioService.listarUsuariosNoSuperAdministrador().stream().filter(s-> s.isActivo())
                    .map(UsuarioDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok().body(usuarioDtos);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Pagína para el frontend de los usuarios eliminados")
    @GetMapping("/userEliminadoPagina")
    public ResponseEntity<?> listarUsuariosEliminadosPagina(Pageable pageable,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los usuarios eliminados paginable");
        try {
            List<UsuarioDto> usuarios = usuarioService.listarUsuarios(pageable).filter(s-> !s.isActivo())
                    .map(UsuarioDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok().body(usuarios);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Listado de usuarios eliminados")
    @GetMapping("/userEliminado")
    public ResponseEntity<?> listarUsuariosEliminados(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los usuarios eliminados");
        try {
            List<UsuarioDto> usuarioDtos = usuarioService.listarUsuarios().stream().filter(s-> !s.isActivo())
                    .map(UsuarioDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok().body(usuarioDtos);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite insertar un usuario")
    @PostMapping("/")
    public ResponseEntity<String> insertarUsuario(@RequestBody UsuarioDtoInsert usuario,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Insertar o reactivar usuario: "+usuario.getUsername());
        try {
            usuario.setActivo(true);
            Validacion.validarUnsupportedOperationException(usuario);
            Optional<Rol> rol = rolRepository.consultarRol(usuario.getRol());
            Optional<Sexo> sexo = sexoService.consultarSexo(usuario.getSexo());
            usuarioService.validarUsuarioInsertar(usuario.getEmail(),usuario.getUsername());
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            if (usuario.isReactivado() == true){
                registroDto.setActividad("Reactivar usuario: "+usuario.getUsername());
                Usuario user = usuarioService.obtenerUsuarioEmailUsernameName(usuario.getEmail(),usuario.getUsername(),usuario.getName(),sexo.get()).get();
                user.setFechaEliminacion(null);
                user.setActivo(true);
                user.setFechaCreacion(Timestamp.valueOf(LocalDateTime.now()));
                usuarioService.modificarUsuario(user);
                registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
                return ResponseEntity.ok().body("Usuario reactivado correctamente");
            }
            registroDto.setActividad("Insertar usuario: "+usuario.getUsername());
            usuarioService.insertarUsuario(new Usuario(usuario,rol.get(),sexo.get()));
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok().body("Usuario insertado correctamente");
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite actualizar un usuario")
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuarioDto usuario,@PathVariable Long id,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Modificar usuario con id: "+id);
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
                registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
                return ResponseEntity.ok().body("Usuario modificado correctamente");
            }
            else {
                registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
                return ResponseEntity.badRequest().body("Usuario no modificado");
            }
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite eliminar un usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Eliminar un usuario por ID");
        try {
            usuarioService.eliminarUsuario(id);
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok("Usuario eliminado");
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazadp");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite conocer si un usuario está activo o no")
    @GetMapping("/isUserActivo")
    public ResponseEntity<String> isUsuarioActivo(UsuarioDto usuario,HttpServletRequest request){
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener si un usuario está activo o no");
        try {
            if (usuarioService.usuarioActivo(usuario.getEmail(), usuario.getUsername()).isPresent()) {
                registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
                return ResponseEntity.ok().body("El usuario está activo");
            }
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.ok("El usuario no está activo");
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
