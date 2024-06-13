package cu.edu.cujae.logs.core.controller;


import cu.edu.cujae.logs.core.clases.Rol;
import cu.edu.cujae.logs.core.utils.Validacion;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rol")
public class RolController {

    @Autowired
    private RolServiceInterfaces rolServiceInterfaces;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody Rol rol) {
        try{
            String resultado = Validacion.comprobacionValidador(Validacion.validador(),rol);
            if (resultado.isEmpty()) {
                rolServiceInterfaces.insertarRol(rol);
            }
            else {
                throw new Exception(resultado);
            }
            return ResponseEntity.ok("Insertado correctamente");
        }
        catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> listarRoles() {
        try {
            return ResponseEntity.ok(rolServiceInterfaces.consultarRol());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarRol(@PathVariable Long id, @RequestBody Rol rol){
        try {
            rolServiceInterfaces.modificarRol(rol,id);
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
}
