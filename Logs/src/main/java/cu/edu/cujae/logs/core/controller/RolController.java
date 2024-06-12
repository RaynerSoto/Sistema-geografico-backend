package cu.edu.cujae.logs.core.controller;


import cu.edu.cujae.logs.core.servicesImpl.RolServiceInterface;
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
    public ResponseEntity<String> pruena() {
        return ResponseEntity.ok("Prueba");
    }


}
