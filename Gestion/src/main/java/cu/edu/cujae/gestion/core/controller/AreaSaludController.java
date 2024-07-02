package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.AreaSaludDto;
import cu.edu.cujae.gestion.core.mapping.AreaSalud;
import cu.edu.cujae.gestion.core.servicesInterfaces.AreaSaludServicesInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;

@RestController
@RequestMapping("/api/v1/areaSalud")
public class AreaSaludController {
    @Autowired
    private AreaSaludServicesInterfaces areaSaludServices;

    @GetMapping("/")
    private ResponseEntity<?> getAreaSalud() {
        try{
            return ResponseEntity.ok(areaSaludServices.getAreaSalud().stream()
                    .map(AreaSaludDto::new)
                    .sorted(Comparator.comparing(AreaSaludDto::getUuid))
                    .toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
