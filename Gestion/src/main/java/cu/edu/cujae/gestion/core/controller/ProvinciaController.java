package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.ProvinciaDto;
import cu.edu.cujae.gestion.core.servicesInterfaces.ProvinciaServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Comparator;

@RestController
@RequestMapping("/api/v1/provincia")
public class ProvinciaController {
    @Autowired
    private ProvinciaServiceInterfaces provinciaService;

    @GetMapping("/")
    public ResponseEntity<?> getAllProvincia() {
        try {
            return ResponseEntity.ok(provinciaService.listadoProvincia().stream()
                    .map(ProvinciaDto::new)
                    .sorted(Comparator.comparing(ProvinciaDto::getUuid))
                    .toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
