package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDto;
import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDtoRegular;
import cu.edu.cujae.gestion.core.servicesInterfaces.EmpleadoServiceInterfaces;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/empleado")
@Tag(name = "Controlador de empleados",
        description = "Controlador encargardo de todo lo referente con los empleados del sistema")
public class EmpleadoController {

    @Autowired
    private EmpleadoServiceInterfaces empleadoService;

    @GetMapping("/")
    public ResponseEntity<?> listarEmpleados(){
        try {
            EmpleadoDto empleadoDto = new EmpleadoDto();
            return ResponseEntity.ok(empleadoService.obtenerEmpleados().stream()
                    .map(empleado -> new EmpleadoDtoRegular(empleado)).toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> insertarEmpleadoSinCompañia(){
        try {
            EmpleadoDto empleadoDto = new EmpleadoDto();
            return null;
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
