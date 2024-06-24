package cu.edu.cujae.logs.core.controller;


import cu.edu.cujae.logs.core.mapping.Rol;
import cu.edu.cujae.logs.core.dto.RolDto;
import cu.edu.cujae.logs.core.utils.Validacion;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/rol")
public class RolController {

    @Autowired
    private RolServiceInterfaces rolServiceInterfaces;

    @PostMapping("/")
    public ResponseEntity<?> crearRol(@RequestBody RolDto rol) {
        try{
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

    @GetMapping("/")
    public ResponseEntity<?> listarRoles() {
        try {
            List<RolDto> roles = rolServiceInterfaces.consultarRol().stream().map(s-> new RolDto(s)).toList();
            return ResponseEntity.ok(roles);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarRol(@PathVariable Long id, @RequestBody RolDto rol){
        try {
            rolServiceInterfaces.modificarRol(new Rol(rol),id);
            return ResponseEntity.ok("Modificado correctamente");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Long id){
        try {
            rolServiceInterfaces.eliminarRol(id);
            return ResponseEntity.ok("Eliminado correctamente");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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
