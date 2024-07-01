package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.ZonaTransporteDto;
import cu.edu.cujae.gestion.core.servicesInterfaces.ZonaTransporteServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/zonaTransporte")
public class ZonaTransporteController {
    @Autowired
    private ZonaTransporteServiceInterfaces zonaTransporteService;

    @GetMapping("/")
    private ResponseEntity<?> getZonaTransporte() {
        try {
            return ResponseEntity.ok(
                    zonaTransporteService.listadoZonaTransporte().stream()
                            .map(ZonaTransporteDto::new).sorted(Comparator.comparing(ZonaTransporteDto::getUuid))
                            .toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
