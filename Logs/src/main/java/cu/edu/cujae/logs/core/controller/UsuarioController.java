package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.logs.core.mapper.Rol;
import cu.edu.cujae.logs.core.mapper.Sexo;
import cu.edu.cujae.logs.core.mapper.Usuario;
import cu.edu.cujae.logs.core.services.RolServiceInterfaces;
import cu.edu.cujae.logs.core.services.SexoServiceInterfaces;
import cu.edu.cujae.logs.core.services.UsuarioServiceInterfaces;
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

// Funcionamiento probado
@RestController
@RequestMapping("/api/v1/login/usuario")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controllador de los usuarios del sistema", description = "Controlla los usuarios registrados en el sistema")
public class UsuarioController {

    private final UsuarioServiceInterfaces usuarioService;
    private final RolServiceInterfaces rolRepository;
    private final SexoServiceInterfaces sexoService;
    private final PasswordEncoder passwordEncoder;
    private final RegistroUtils registroUtils;
    private final TokenUtils tokenUtils;

    @Autowired
    public UsuarioController(UsuarioServiceInterfaces usuarioService, RolServiceInterfaces rolRepository, SexoServiceInterfaces sexoService, PasswordEncoder passwordEncoder, RegistroUtils registroUtils, TokenUtils tokenUtils) {
        this.usuarioService = usuarioService;
        this.rolRepository = rolRepository;
        this.sexoService = sexoService;
        this.passwordEncoder = passwordEncoder;
        this.registroUtils = registroUtils;
        this.tokenUtils = tokenUtils;
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Listado de todos los usuarios independientemente de su estado")
    @GetMapping("/")
    public ResponseEntity<?> listarAllUsuarios(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los usuarios");
        try {
            List<UsuarioDto> user = usuarioService.listarUsuarios().stream().map(UsuarioDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body(user);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se ha podido obtener el listado de usuarios, compruebe su conexiòn a la base de datos o contacto con el servicio tècnico");
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
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body(usuarioDtos);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",null);
            return ResponseEntity.badRequest().body("No se ha podido obtener el listado de usuarios, compruebe su conexiòn a la base de datos o contacto con el servicio tècnico");
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
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body(usuarioDtos);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se ha podido obtener el listado de usuarios, compruebe su conexiòn a la base de datos o contacto con el servicio tècnico");
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
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body(usuarioDtos);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se ha podido obtener el listado de usuarios, compruebe su conexiòn a la base de datos o contacto con el servicio tècnico");
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
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body(usuarios);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se ha podido obtener el listado de usuarios, compruebe su conexiòn a la base de datos o contacto con el servicio tècnico");
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
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body(usuarioDtos);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se ha podido obtener el listado de usuarios, compruebe su conexiòn a la base de datos o contacto con el servicio tècnico");
        }
    }

    @PreAuthorize(value = "hasAnyRole('Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite insertar un usuario")
    @PostMapping("/")
    public ResponseEntity<String> insertarUsuario(@RequestBody UsuarioDto usuario,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Insertar usuario: "+usuario.getUsername());
        try {
            usuario.setActivo(true);
            if(usuario.getUsername().contains(" "))
                throw new Exception("Los nombres de usuarios no puede contener espacios");
            Validacion.validarUnsupportedOperationException(usuario);
            Optional<Rol> rol = rolRepository.consultarRol(usuario.getRol());
            if (rol.get().getRol().equalsIgnoreCase("Super Administrador"))
                throw new Exception("No tiene permitido dicha funcionalidad");
            Optional<Sexo> sexo = sexoService.consultarSexo(usuario.getSexo());
            usuarioService.validarUsuarioInsertar(usuario.getEmail(),usuario.getUsername());
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioService.insertarUsuario(new Usuario(usuario,rol.get(),sexo.get()));
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body("Usuario insertado correctamente");
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
            ,summary = "Permite insertar un usuario super administrador")
    @PostMapping("/todosRoles")
    public ResponseEntity<String> insertarUsuarioSuperAdministrador(@RequestBody UsuarioDto usuario,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Insertar usuario Super Administrador: "+usuario.getUsername());
        try {
            usuario.setActivo(true);
            if(usuario.getUsername().contains(" "))
                throw new Exception("Los nombres de usuarios no puede contener espacios");
            Validacion.validarUnsupportedOperationException(usuario);
            Optional<Rol> rol = rolRepository.consultarRol(usuario.getRol());
            Optional<Sexo> sexo = sexoService.consultarSexo(usuario.getSexo());
            usuarioService.validarUsuarioInsertar(usuario.getEmail(),usuario.getUsername());
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioService.insertarUsuario(new Usuario(usuario,rol.get(),sexo.get()));
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body("Usuario insertado correctamente");
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
            ,summary = "Permite reactivar un usuario completo")
    @PutMapping("/")
    @Deprecated
    public ResponseEntity<String> reactivarUsuarioCompleto(@RequestBody UsuarioDto usuario,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Reactivar usuario completo: "+usuario.getUsername());
        try {
            usuario.setActivo(true);
            Validacion.validarUnsupportedOperationException(usuario);
            Optional<Rol> rol = rolRepository.consultarRol(usuario.getRol());
            Optional<Sexo> sexo = sexoService.consultarSexo(usuario.getSexo());
            usuarioService.validarUsuarioInsertar(usuario.getEmail(),usuario.getUsername());
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            registroDto.setActividad("Reactivar usuario: "+usuario.getUsername());
            if (usuarioService.obtenerUsuarioUsernameInactivo(usuario.getUsername()).isPresent())
                throw new Exception("No existe ese nombre usuario para reactivar");
            Usuario user = usuarioService.obtenerUsuarioEmailUsernameName(usuario.getEmail(),usuario.getUsername(),usuario.getName(),sexo.get()).get();
            user.setFechaEliminacion(null);
            user.setActivo(true);
            user.setFechaCreacion(Timestamp.valueOf(LocalDateTime.now()));
            usuarioService.modificarUsuario(user);
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body("Usuario reactivado correctamente");
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
            ,summary = "Permite reactivar un usuario a travès del nombre de usuario")
    @PutMapping("/{username}")
    public ResponseEntity<String> reactivarUsuarioUsername(@PathVariable(name = "username") String usuario,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Reactivar usuario por su nombre de usuario: "+usuario);
        try {
            registroDto.setActividad("Reactivar usuario: "+usuario);
            Usuario user = usuarioService.obtenerUsuarioUsernameInactivo(usuario).get();
            user.setFechaEliminacion(null);
            user.setActivo(true);
            user.setFechaCreacion(Timestamp.valueOf(LocalDateTime.now()));
            usuarioService.modificarUsuario(user);
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body("Usuario reactivado correctamente");
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
            ,summary = "Permite reactivar un usuario a travès del id de usuario")
    @PutMapping("/ID/{id}")
    public ResponseEntity<String> reactivarUsuarioUsername(@PathVariable(name = "id") Long usuarioID,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Reactivar usuario por su id de usuario: "+usuarioID);
        try {
            Usuario user = usuarioService.buscarUsuario(usuarioID);
            user.setFechaEliminacion(null);
            user.setActivo(true);
            user.setFechaCreacion(Timestamp.valueOf(LocalDateTime.now()));
            usuarioService.modificarUsuario(user);
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body("Usuario reactivado correctamente");
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite actualizar un usuario")
    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> actualizarUsuario(@RequestBody UsuarioDto usuario,@PathVariable Long id,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Modificar usuario con id: "+id);
        try {
            Validacion.validarUnsupportedOperationException(usuario);
            usuario.setUuid(id);
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            Optional<Rol> rol = rolRepository.consultarRol(usuario.getRol());
            Usuario valor = usuarioService.buscarUsuario(id);
            if (valor.isActivo() == false)
                throw new Exception("No se puede modificar un usuario que eliminado");
            usuario.setActivo(valor.isActivo());
            usuario.setFechaCreacion(valor.getFechaCreacion());
            usuario.setFechaEliminacion(valor.getFechaEliminacion());
            if (rol.get().getRol().equalsIgnoreCase("Super Administrador") || valor.getRol().getRol().equalsIgnoreCase("Super Administrador"))
                throw new Exception("Operación no soportada");
            Optional<Sexo> sexo = sexoService.consultarSexo(usuario.getSexo());
            usuarioService.validarUsuarioModificar(usuario.getEmail(),usuario.getUsername(),usuario.getUuid());
            if (rol.isPresent() && sexo.isPresent() && usuario.isActivo() == true) {
                Usuario user = new Usuario(usuario,rol.get(),sexo.get());
                usuarioService.modificarUsuario(user);
                registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
                return ResponseEntity.ok().body("Usuario modificado correctamente");
            }
            else {
                registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado","Realizado un intento de modificar un usurio no activo");
                return ResponseEntity.badRequest().body("Usuario no modificado");
            }
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite eliminar un usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Eliminar un usuario por ID");
        try {
            if(usuarioService.buscarUsuario(id).getRol().getRol().equalsIgnoreCase("Super Administrador") == true){
                throw new Exception("Operacion no soportada");
            }
            usuarioService.eliminarUsuario(id);
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok("Usuario eliminado");
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
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
                registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
                return ResponseEntity.ok().body("El usuario está activo");
            }
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado","El usuario no està activo");
            return ResponseEntity.ok("El usuario no está activo");
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
