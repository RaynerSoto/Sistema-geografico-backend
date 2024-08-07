package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.AreaSaludDto;
import cu.edu.cujae.gestion.core.mapping.AreaSalud;
import cu.edu.cujae.gestion.core.servicesInterfaces.AreaSaludServicesInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;

@RestController
@RequestMapping("/api/v1/areaSalud")
@Tag(name = "Controlador del área de salud"
        ,description = "Permite hacer todas las operaciones relacionadas con el área de salud")
public class AreaSaludController {
    private final AreaSaludServicesInterfaces areaSaludServices;

    @Autowired
    public AreaSaludController(AreaSaludServicesInterfaces areaSaludServices) {
        this.areaSaludServices = areaSaludServices;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de áreas de salud",
            description = "Permite obtener todo el listado de las áreas de salud")
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
