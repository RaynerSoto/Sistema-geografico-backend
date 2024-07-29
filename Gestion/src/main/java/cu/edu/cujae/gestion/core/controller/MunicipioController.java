package cu.edu.cujae.gestion.core.controller;
import cu.edu.cujae.gestion.core.dto.MunicipioDto;
import cu.edu.cujae.gestion.core.servicesInterfaces.MunicipioServicesInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;

@RestController
@RequestMapping(value = "/api/v1/municipio")
@Tag(name = "Controlador de municipios",
        description = "Permite hacer todas las operaciones de los municipios")
public class MunicipioController {
    @Autowired
    private MunicipioServicesInterfaces municipioServices;

    @GetMapping("/")
    @Operation(summary = "Listado de municipios",
    description = "Permite obtener el listado de todos los municipios del sistema")
    public ResponseEntity<?> getAllMunicipios() {
        try {
            return ResponseEntity.ok(municipioServices.listadoMunicipios().stream()
                    .map(MunicipioDto::new)
                    .sorted(Comparator.comparing(MunicipioDto::getUuid))
                    .toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/Provincia")
    @Operation(summary = "Listado de municipios por provincia",
            description = "Permite obtener el listado de todos los municipios pertenecientes a una provincia")
    public ResponseEntity<?> getAllMunicipiosXProvincia(String provincia) {
        try {
            return ResponseEntity.ok(municipioServices.listadoMunicipios().stream()
                    .map(MunicipioDto::new)
                    .filter(municipioDto -> municipioDto.getProvincia().equalsIgnoreCase(provincia))
                    .sorted(Comparator.comparing(MunicipioDto::getUuid))
                    .toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
