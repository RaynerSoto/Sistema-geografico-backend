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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @Operation(summary = "Listado de empleados"
            ,description = "Permite listar todos los empleados del sistema junto con sus centros laborales")
    public ResponseEntity<?> listarEmpleados(){
        try {
            return ResponseEntity.ok(empleadoService.obtenerEmpleados().stream()
                    .map(empleado -> new EmpleadoDtoRegular(empleado)).toList());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/")
    @Operation(summary = "Insertar empleado sin trabajo",
    description = "Permite insertar un empleado que no está afiliado a un centro laboral")
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
    @Operation(summary = "Insertar empleado con trabajo",
    description = "Permite insertar un empleado que está afiliado a un centro laboral ")
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

    @GetMapping("/{ci}")
    @Operation(summary = "Obtener empleado por CI",
    description = "Permite obtener un empleado a raíz de su carnet de identidad")
    public ResponseEntity<?> obtenerEmpleadosPorCi(@PathVariable String ci){
        try {
            EmpleadoDtoRegular empleado = new EmpleadoDtoRegular(empleadoService.obtenerEmpleadoXCi(ci).get());
            return ResponseEntity.ok(empleado);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener empleado por ID",
            description = "Permite obtener un empleado a raíz de su ID")
    public ResponseEntity<?> obtenerEmpleadosPorId(@PathVariable Long id){
        try {
            EmpleadoDtoRegular empleado = new EmpleadoDtoRegular(empleadoService.obtenerEmpleadoXId(id).get());
            return ResponseEntity.ok(empleado);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar empleado por ID",
            description = "Permite modificar un empleado a raíz de su ID")
    public ResponseEntity<?> modificarEmpleadoXId(@PathVariable Long id,@RequestBody EmpleadoDto empleadoDto){
        try {
            Validacion.validarUnsupportedOperationException(empleadoDto);
            empleadoDto.setUuid(id);
            Optional<Provincia> provincia = provinciaService.buscarProvinciaPorNombre(empleadoDto.getProvincia());
            Optional<Municipio> municipio = municipioService.obtenerMunicipioNombre(empleadoDto.getMunicipio());
            if (!municipioService.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
                return ResponseEntity.badRequest().body("Este municipio no pertenece a la provincia");
            empleadoService.modificarEmpleado(new Empleado(empleadoDto,municipio.get(),provincia.get()));
            return ResponseEntity.ok("Usuario insertado con éxito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar empleado por ID",
            description = "Permite eliminar un empleado a raíz de su ID")
    public ResponseEntity<?> eliminarEmpleadoXId(@PathVariable Long id){
        try {
            empleadoService.eliminarEmpleado(id);
            return ResponseEntity.ok("Empleado eliminado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{ci}")
    @Operation(summary = "Eliminar empleado por CI",
            description = "Permite eliminar un empleado a raíz de su carnet de identidad")
    public ResponseEntity<?> eliminarEmpleadoXCi(@PathVariable String ci){
        try {
            empleadoService.eliminarEmpleado(ci);
            return ResponseEntity.ok("Empleado eliminado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
