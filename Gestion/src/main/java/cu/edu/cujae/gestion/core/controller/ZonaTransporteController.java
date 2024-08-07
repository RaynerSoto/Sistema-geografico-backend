package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.ZonaTransporteDto;
import cu.edu.cujae.gestion.core.servicesInterfaces.ZonaTransporteServiceInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;

@RestController
@RequestMapping("/api/v1/zonaTransporte")
@Tag(name = "Controllador de zona de transporte")
public class ZonaTransporteController {

    private final ZonaTransporteServiceInterfaces zonaTransporteService;

    @Autowired
    public ZonaTransporteController(ZonaTransporteServiceInterfaces zonaTransporteService) {
        this.zonaTransporteService = zonaTransporteService;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de zonas de transportes",
            description = "Permite obtener el listado de todas las zonas de transportes del sistema")
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
