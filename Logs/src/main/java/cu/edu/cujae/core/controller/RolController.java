package cu.edu.cujae.core.controller;


import cu.edu.cujae.core.clases.Rol;
import cu.edu.cujae.core.servicesImpl.RolServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prueba")
public class RolController {

    @Autowired
    private RolServiceInterface rolServiceInterface;

    @GetMapping("/")
    public ResponseEntity<Rol> prueba() {
        return ResponseEntity.ok(rolServiceInterface.hola());
    }
}
