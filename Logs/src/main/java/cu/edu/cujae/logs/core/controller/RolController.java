package cu.edu.cujae.logs.core.controller;


import cu.edu.cujae.logs.core.mapping.Rol;
import cu.edu.cujae.logs.core.dto.RolDto;
import cu.edu.cujae.logs.core.utils.Validacion;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;
import inet.ipaddr.ipv4.IPv4Address;
import inet.ipaddr.ipv6.IPv6Address;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@RequestMapping("/api/v1/rol")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controlador de los roles",description = "Determina todo lo relacionado con las funcionalidades de los roles del proyecto")
public class RolController {

    @Autowired
    private RolServiceInterfaces rolServiceInterfaces;

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite insertar un rol")
    @PostMapping("/")
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
    public ResponseEntity<?> listarRoles() {
        try {
            return ResponseEntity.ok(rolServiceInterfaces.consultarRol().stream().map(RolDto::new).toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite ver solamente los nombres de los roles")
    @GetMapping("/nombresRol")
    public ResponseEntity<?> listarRolesNombre() {
        try {
            return ResponseEntity.ok(rolServiceInterfaces.consultarRol().stream().map(s-> s.getRol()).toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite consultar un rol por el ID")
    @GetMapping("/{id}")
    public ResponseEntity<?> consultarRol(@PathVariable Long id) {
        try {
            Optional<Rol> rol = rolServiceInterfaces.consultarRolID(id);
            if (rol.isPresent()) {
                return ResponseEntity.ok(new RolDto(rol.get()));
            }
            else {
                return ResponseEntity.badRequest().body("El rol no existe");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Permite modificar un rol")
    @PutMapping("/{id}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long id){
        try {
            rolServiceInterfaces.eliminarRol(id);
            return ResponseEntity.ok("Eliminado correctamente");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Permite buscar un rol por su nombre")
    @GetMapping("/buscadorNombre")
    public ResponseEntity<?> buscadorRolNombre(RolDto rol){
        try {
            return ResponseEntity.ok(new RolDto(rolServiceInterfaces.consultarRol(rol.getNombre())));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
