package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDto;
import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDtoInsert;
import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDtoRegular;
import cu.edu.cujae.gestion.core.mapping.Empleado;
import cu.edu.cujae.gestion.core.mapping.Entidad;
import cu.edu.cujae.gestion.core.mapping.Municipio;
import cu.edu.cujae.gestion.core.mapping.Provincia;
import cu.edu.cujae.gestion.core.servicesInterfaces.EmpleadoServiceInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.EntidadServicesInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.MunicipioServicesInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.ProvinciaServiceInterfaces;
import cu.edu.cujae.gestion.core.utils.Validacion;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empleado")
@Tag(name = "Controlador de empleados",
        description = "Controlador encargardo de todo lo referente con los empleados del sistema")
public class EmpleadoController {

    @Autowired
    private EmpleadoServiceInterfaces empleadoService;
    @Autowired
    private MunicipioServicesInterfaces municipioService;
    @Autowired
    private ProvinciaServiceInterfaces provinciaService;
    @Autowired
    private EntidadServicesInterfaces entidadService;

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
    public ResponseEntity<?> insertarEmpleadoSinCompañia(EmpleadoDto empleadoDto){
        try {
            Validacion.validarUnsupportedOperationException(empleadoDto);
            Optional<Provincia> provincia = provinciaService.buscarProvinciaPorNombre(empleadoDto.getProvincia());
            Optional<Municipio> municipio = municipioService.obtenerMunicipioNombre(empleadoDto.getMunicipio());
            if (!municipioService.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
                return ResponseEntity.badRequest().body("Este municipio no pertenece a la provincia");
            empleadoService.insertarEmpleado(new Empleado(empleadoDto,municipio.get(),provincia.get()));
            return ResponseEntity.ok("Usuario insertado con éxito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/compannia")
    public ResponseEntity<?> insertarEmpleadoConCompañia(EmpleadoDtoInsert empleadoDto){
        try {
            Validacion.validarUnsupportedOperationException(empleadoDto);
            Optional<Entidad> entidad = entidadService.obtenerEntidadNombre(empleadoDto.getEntidad());
            Optional<Provincia> provincia = provinciaService.buscarProvinciaPorNombre(empleadoDto.getProvincia());
            Optional<Municipio> municipio = municipioService.obtenerMunicipioNombre(empleadoDto.getMunicipio());
            if (!municipioService.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
                return ResponseEntity.badRequest().body("Este municipio no pertenece a la provincia");
            entidad.get().getPersonal().add(new Empleado(empleadoDto,municipio.get(),provincia.get()));
            entidadService.modificarEntidad(entidad.get());
            return ResponseEntity.ok("Usuario insertado con éxito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
