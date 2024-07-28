package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.servicesInterfaces.EntidadServicesInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/entidad")
public class EntidadController {
    @Autowired
    private EntidadServicesInterfaces entidadServices;

    @GetMapping("/")
    public ResponseEntity<?> listadoEntidades(){

    }

}
