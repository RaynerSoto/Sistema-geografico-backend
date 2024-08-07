package cu.edu.cujae.logs.core.controller;


import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.mapping.Rol;
import cu.edu.cujae.logs.core.dto.RolDto;
import cu.edu.cujae.logs.core.utils.RegistroUtils;
import cu.edu.cujae.logs.core.utils.TokenUtils;
import cu.edu.cujae.logs.core.utils.Validacion;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/rol")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controlador de los roles",description = "Determina todo lo relacionado con las funcionalidades de los roles del proyecto")
public class RolController {

    private RolServiceInterfaces rolServiceInterfaces;
    private RegistroUtils registroUtils;
    private TokenUtils tokenUtils;

    @Autowired
    public RolController(RolServiceInterfaces rolServiceInterfaces, RegistroUtils registroUtils, TokenUtils tokenUtils) {
        this.rolServiceInterfaces = rolServiceInterfaces;
        this.registroUtils = registroUtils;
        this.tokenUtils = tokenUtils;
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite insertar un rol")
    //@PostMapping("/")
    @Deprecated
    public ResponseEntity<?> crearRol(@RequestBody RolDto rol) {
        try{
            Validacion.validarUnsupportedOperationException(rol);
            if (rolServiceInterfaces.consultarRolNombre(rol.getNombre()).isPresent() == false){
                rolServiceInterfaces.insertarRol(new Rol(rol));
                return ResponseEntity.ok("Insertado correctamente");
            } else {
                return ResponseEntity.badRequest().body("El rol ya existe");
            }
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite ver todos los roles")
    @GetMapping("/")
    public ResponseEntity<?> listarRoles(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado de roles");
        try {
            List<RolDto>roles = rolServiceInterfaces.consultarRol().stream().map(RolDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok(roles);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite ver solamente los nombres de los roles")
    @GetMapping("/nombresRol")
    public ResponseEntity<?> listarRolesNombre(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener los nombres de los roles");
        try {
            List<String> rolDtos = rolServiceInterfaces.consultarRol().stream().map(s-> s.getRol()).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok(rolDtos);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite consultar un rol por el ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> consultarRol(@PathVariable Long id,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener los nombres de los roles");
        try {
            Optional<Rol> rol = rolServiceInterfaces.consultarRolID(id);
            if (rol.isPresent()) {
                registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
                return ResponseEntity.ok(new RolDto(rol.get()));
            }
            else {
                registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
                return ResponseEntity.badRequest().body("El rol no existe");
            }
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite modificar un rol")
    //@PutMapping("/{id}")
    @Deprecated
    public ResponseEntity<?> modificarRol(@PathVariable Long id, @RequestBody RolDto rol){
        try {
            rolServiceInterfaces.modificarRol(new Rol(rol),id);
            return ResponseEntity.ok("Modificado correctamente");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite eliminar un rol")
    //@DeleteMapping("/{id}")
    @Deprecated
    public ResponseEntity<?> eliminarRol(@PathVariable Long id,HttpServletRequest request){
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Eliminar un rol por su ID");
        try {
            rolServiceInterfaces.eliminarRol(id);
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok("Eliminado correctamente");
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite buscar un rol por su nombre")
    @GetMapping("/buscadorNombre")
    public ResponseEntity<?> buscadorRolNombre(RolDto rol,HttpServletRequest request){
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Buscar un rol por su nombre");
        try {
            RolDto rolDto = new RolDto(rolServiceInterfaces.consultarRol(rol.getNombre()));
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok(rolDto);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
