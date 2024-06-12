package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.services.PrivilegioService;
import cu.edu.cujae.logs.core.servicesInterfaces.PrivilegioServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/privilegio")
public class PrivilegioController {
    @Autowired
    private PrivilegioServiceInterfaces privilegioService;

    @GetMapping("/")
    public ResponseEntity<?> listarPrivilegios() {
        try {
            return ResponseEntity.ok().body(privilegioService.listarPrivilegios());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
