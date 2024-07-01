package cu.edu.cujae.gestion.core.controller;
import cu.edu.cujae.gestion.core.dto.MunicipioDto;
import cu.edu.cujae.gestion.core.servicesInterfaces.MunicipioServicesInterfaces;
import cu.edu.cujae.gestion.core.utils.GeoJson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cu.edu.cujae.gestion.core.utils.*;

import java.util.Comparator;

@RestController
@RequestMapping("/api/v1/municipio")
public class MunicipioController {
    @Autowired
    private MunicipioServicesInterfaces municipioServices;

    @GetMapping("/")
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
