package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.EntidadDto;
import cu.edu.cujae.gestion.core.servicesInterfaces.EntidadServicesInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/entidad",name = "Controller para las entidades")
public class EntidadController {

    @Autowired
    private EntidadServicesInterfaces entidadServices;

    @GetMapping("/")
    public ResponseEntity<?> listadoEntidades(){
        try {
            return ResponseEntity.ok(entidadServices.listarEntidad());
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error en cargar el listado de entidades");
        }
    }

    @PostMapping(value = "/",name = "Método para insertar entidad")
    public ResponseEntity<?> insertarEntidad(@RequestBody EntidadDto entidad){
        return null;
    }
}
