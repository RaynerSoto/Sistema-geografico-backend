package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.ProvinciaDto;
import cu.edu.cujae.gestion.core.servicesInterfaces.ProvinciaServiceInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Comparator;

@RestController
@RequestMapping("/api/v1/provincia")
@Tag(name = "Controllador de provincias",
        description = "Controllador encargado de todo lo relacionado con las provincias del país")
public class ProvinciaController {
    @Autowired
    private ProvinciaServiceInterfaces provinciaService;

    @GetMapping("/")
    @Operation(summary = "Listado de provincias",
            description = "Permite obtener el listado de todos las provincias del sistema")
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

    @GetMapping("/nombre")
    @Operation(summary = "Obtener provincias por nombre",
            description = "Obtener todos los datos de la provincia según su nombre")
    public ResponseEntity<?> getProvinciaNombre(String nombre) {
        try {
            return ResponseEntity.ok(new ProvinciaDto(provinciaService.buscarProvinciaPorNombre(nombre)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
