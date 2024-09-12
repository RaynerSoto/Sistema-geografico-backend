package cu.edu.cujae.logs.core.controller;


import cu.edu.cujae.logs.core.dto.Generic;
import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.logs.core.mapper.Rol;
import cu.edu.cujae.logs.core.dto.RolDto;
import cu.edu.cujae.logs.core.mapper.Usuario;
import cu.edu.cujae.logs.core.utils.RegistroUtils;
import cu.edu.cujae.logs.core.utils.TokenUtils;
import cu.edu.cujae.logs.core.utils.Validacion;
import cu.edu.cujae.logs.core.services.RolServiceInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/login/rol")
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
    @PostMapping("/denegado/")
    @Deprecated
    public ResponseEntity<?> crearRol(@RequestBody RolDto rol,HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Insertar Rol");
        try{
            Validacion.validarUnsupportedOperationException(rol);
            if (rolServiceInterfaces.consultarRolNombre(rol.getNombre()).isPresent() == false){
                rolServiceInterfaces.insertarRol(new Rol(rol));
                registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
                return ResponseEntity.ok("Insertado correctamente");
            } else {
                throw new Exception("El rol ya existe");
            }
        }
        catch(Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
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
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok(roles);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("Compruebe la conexiòn con la base datos o consulte con el servicio tècnico");
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite ver todos los nombres de los roles")
    @GetMapping("/roles")
    public ResponseEntity<?> listarRolesNombre(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado de los nombres de los roles");
        try {
            List<String>roles = rolServiceInterfaces.consultarRol().stream().map(Rol::getRol).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok(roles);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("Compruebe la conexiòn con la base datos o consulte con el servicio tècnico");
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Permite ver solamente los roles que no son Super Administradores")
    @GetMapping("/RolNoSuperAdmin")
    public ResponseEntity<?> listarRolesNoSuperAdministradores(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener los roles que no son super administradores");
        try {
            List<RolDto> rolDtos = rolServiceInterfaces.consultarRolNoSuperAdministrador().stream().map(RolDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok(rolDtos);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            summary = "Permite ver solamente los nombres de los roles que no son Super Administradores")
    @GetMapping("/nombresRolNoSuperAdminNombre")
    public ResponseEntity<?> listarRolesNoSuperAdministradoresNombre(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener los nombres de los roles que no son super administradores");
        try {
            List<String> rolDtos = rolServiceInterfaces.consultarRolNoSuperAdministrador().stream().map(s-> s.getRol()).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok(rolDtos);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
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
            if (rol.isPresent() == false) {
                throw new Exception("El rol no existe");
            }
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok(new RolDto(rol.get()));
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('No Rol')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite modificar un rol")
    @PutMapping("/denegado/{id}")
    @Deprecated
    public ResponseEntity<?> modificarRol(@PathVariable Long id, @RequestBody RolDto rol){
        try {
            rolServiceInterfaces.modificarRol(new Rol(rol),id);
            return ResponseEntity.ok("Modificado correctamente");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyRole('No Rol')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite eliminar un rol")
    @DeleteMapping("/denegado/{id}")
    @Deprecated
    public ResponseEntity<?> eliminarRol(@PathVariable Long id,HttpServletRequest request){
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Eliminar un rol por su ID");
        try {
            rolServiceInterfaces.eliminarRol(id);
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok("Eliminado correctamente");
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
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
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok(rolDto);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se ha podido encontrar el rol buscado");
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite ver las relación entre el rol, la cantidad de usuarios y que usuarios son exactamente")
    @GetMapping("/reportes/reportesRolesCantidadUsuariosUsers")
    public ResponseEntity<?> reportesRolesUsuariosCantidad(HttpServletRequest request){
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Observar un listado de los roles con la cantidad de usuarios y que usuarios son");
        try{
            List<Rol> roles = rolServiceInterfaces.consultarRol();
            List<Generic> generics = new ArrayList<>();
            for(Rol rol : roles){
                generics.add(new Generic(new RolDto(rol),rol.getUsuarioList().size(), rol.getUsuarioList().stream().map(UsuarioDto::new).toList()));
            }
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok(generics);
        }catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se ha podido obtener acceso al reporte solicitado. Contacto con el servicio técnico");
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite ver las relación entre el rol y la cantidad de usuarios")
    @GetMapping("/reportes/reportesRolesCantidadUsuarios")
    public ResponseEntity<?> reportesRolesUsuarios(HttpServletRequest request){
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Observar un listado de los roles con la cantidad de usuarios y que usuarios son");
        try {
           List<Rol> roles = rolServiceInterfaces.consultarRol();
           List<Generic> generics = new ArrayList<>();
           for(Rol rol : roles){
               generics.add(new Generic(new RolDto(rol),rol.getUsuarioList().size()));
           }
           registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
           return ResponseEntity.ok(generics);
       }catch (Exception e){
           registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
           return ResponseEntity.badRequest().body("No se ha podido obtener acceso al reporte solicitado. Contacto con el servicio técnico");
       }
    }


}
