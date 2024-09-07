package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.mapper.Provincia;
import cu.edu.cujae.gestion.core.services.EmpleadoServiceInterfaces;
import cu.edu.cujae.gestion.core.services.EntidadServicesInterfaces;
import cu.edu.cujae.gestion.core.services.ProvinciaServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gestion/demografia")
public class DemografíaController {
    private final EntidadServicesInterfaces entidadServices;
    private final EmpleadoServiceInterfaces empleadoServices;
    private final ProvinciaServiceInterfaces provinciaServices;

    @Autowired
    public DemografíaController(EntidadServicesInterfaces entidadServices, EmpleadoServiceInterfaces empleadoServices, ProvinciaServiceInterfaces provinciaServices) {
        this.entidadServices = entidadServices;
        this.empleadoServices = empleadoServices;
        this.provinciaServices = provinciaServices;
    }

    @GetMapping("/")
    public ResponseEntity<?> movilidadPoblacional(){
        return null;
    }

}
