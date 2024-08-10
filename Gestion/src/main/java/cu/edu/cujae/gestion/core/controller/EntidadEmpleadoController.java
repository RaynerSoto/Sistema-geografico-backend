package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.mapping.Empleado;
import cu.edu.cujae.gestion.core.mapping.Entidad;
import cu.edu.cujae.gestion.core.servicesInterfaces.EmpleadoServiceInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.EntidadServicesInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/gestion/entidadEmpleado")
@Tag(name = "Controlador del empleador"
        ,description = "Permite todo el proceso de control de los empleados de diversas entidades")
public class EntidadEmpleadoController {

    private final EntidadServicesInterfaces entidadServices;
    private final EmpleadoServiceInterfaces empleadoServices;

    @Autowired
    public EntidadEmpleadoController(EntidadServicesInterfaces entidadServices, EmpleadoServiceInterfaces empleadoServices) {
        this.entidadServices = entidadServices;
        this.empleadoServices = empleadoServices;
    }

    @PostMapping("/{nombre}/{ci}")
    @Operation(summary = "Asignar empleado por nombre y carnet de identidad",
    description = "Permite asignar un empleado a una entidad a través del nombre del centro y el carnet del empleado")
    public ResponseEntity<?> asignacionEmpleados(@PathVariable String nombre,@PathVariable String ci){
        try {
            Optional<Entidad> entidad = entidadServices.obtenerEntidadNombre(nombre);
            Optional<Empleado> empleado = empleadoServices.obtenerEmpleadoXCiException(ci);
            entidad.get().getPersonal().add(empleado.get());
            entidadServices.modificarEntidad(entidad.get());
            return ResponseEntity.ok("Trabajador asignado correctamente");
        }catch (Exception e){
            if (e.getMessage().contains("ukgnv576a6jw8egptmch4dph4xx"))
                return ResponseEntity.badRequest().body("Ya fue asignado dicho trabajador a la entidad");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{nombre}/{id}")
    @Operation(summary = "Asignar empleado por nombre e id",
            description = "Permite asignar un empleado a una entidad a través del nombre del centro y el id del empleado")
    public ResponseEntity<?> asignacionEmpleados(@PathVariable String nombre,@PathVariable Long id){
        try {
            Optional<Entidad> entidad = entidadServices.obtenerEntidadNombre(nombre);
            Optional<Empleado> empleado = empleadoServices.obtenerEmpleadoXId(id);
            entidad.get().getPersonal().add(empleado.get());
            entidadServices.modificarEntidad(entidad.get());
            return ResponseEntity.ok("Trabajador asignado correctamente");
        }catch (Exception e){
            if (e.getMessage().contains("ukgnv576a6jw8egptmch4dph4xx"))
                return ResponseEntity.badRequest().body("Ya fue asignado dicho trabajador a la entidad");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/{ci}")
    @Operation(summary = "Asignar empleado por id y carnet de identidad",
            description = "Permite asignar un emplado a una entidad a través del id del centro y el carnet del empleado")
    public ResponseEntity<?> asignacionEmpleados(@PathVariable Long id,@PathVariable String ci){
        try {
            Optional<Entidad> entidad = entidadServices.obtenerEntidadID(id);
            Optional<Empleado> empleado = empleadoServices.obtenerEmpleadoXCiException(ci);
            entidad.get().getPersonal().add(empleado.get());
            entidadServices.modificarEntidad(entidad.get());
            return ResponseEntity.ok("Trabajador asignado correctamente");
        }catch (Exception e){
            if (e.getMessage().contains("ukgnv576a6jw8egptmch4dph4xx"))
                return ResponseEntity.badRequest().body("Ya fue asignado dicho trabajador a la entidad");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/{id1}")
    @Operation(summary = "Asignar empleado por id e id",
            description = "Permite asignar un empleado a una entidad a través del id del centro y el id del empleado")
    public ResponseEntity<?> asignacionEmpleados(@PathVariable(value = "id") Long idEntidad,@PathVariable(value = "id1") Long idEmpleado){
        try {
            Optional<Entidad> entidad = entidadServices.obtenerEntidadID(idEntidad);
            Optional<Empleado> empleado = empleadoServices.obtenerEmpleadoXId(idEmpleado);
            entidad.get().getPersonal().add(empleado.get());
            entidadServices.modificarEntidad(entidad.get());
            return ResponseEntity.ok("Trabajador asignado correctamente");
        }catch (Exception e){
            if (e.getMessage().contains("ukgnv576a6jw8egptmch4dph4xx"))
                return ResponseEntity.badRequest().body("Ya fue asignado dicho trabajador a la entidad");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{nombre1}/{ci1}")
    @Operation(summary = "Eliminar empleado por nombre y carnet de identidad",
            description = "Permite eliminar un empleado a una entidad a través del nombre del centro y el carnet del empleado")
    public ResponseEntity<?> eliminarEmpleados(@PathVariable(value = "nombre1") String nombre,@PathVariable(value = "ci1") String ci){
        try {
            Optional<Entidad> entidad = entidadServices.obtenerEntidadNombre(nombre);
            Optional<Empleado> empleado = empleadoServices.obtenerEmpleadoXCiException(ci);
            entidad.get().getPersonal().remove(empleado.get());
            entidadServices.modificarEntidad(entidad.get());
            return ResponseEntity.ok("Trabajador asignado correctamente");
        }catch (Exception e){
            if (e.getMessage().contains("ukgnv576a6jw8egptmch4dph4xx"))
                return ResponseEntity.badRequest().body("Ya fue asignado dicho trabajador a la entidad");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{nombre1}/{id1}")
    @Operation(summary = "Eliminar empleado por nombre e id",
            description = "Permite eliminar un empleado a una entidad a través del nombre del centro y el id del empleado")
    public ResponseEntity<?> eliminarEmpleados(@PathVariable(value = "nombre1") String nombre,@PathVariable(value = "id1") Long id){
        try {
            Optional<Entidad> entidad = entidadServices.obtenerEntidadNombre(nombre);
            Optional<Empleado> empleado = empleadoServices.obtenerEmpleadoXId(id);
            entidad.get().getPersonal().remove(empleado.get());
            entidadServices.modificarEntidad(entidad.get());
            return ResponseEntity.ok("Trabajador asignado correctamente");
        }catch (Exception e){
            if (e.getMessage().contains("ukgnv576a6jw8egptmch4dph4xx"))
                return ResponseEntity.badRequest().body("Ya fue asignado dicho trabajador a la entidad");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id1}/{ci1}")
    @Operation(summary = "Eliminar empleado por id y carnet de identidad",
            description = "Permite eliminar un emplado a una entidad a través del id del centro y el carnet del empleado")
    public ResponseEntity<?> eliminarEmpleados(@PathVariable(value = "id1") Long id,@PathVariable(value = "ci1") String ci){
        try {
            Optional<Entidad> entidad = entidadServices.obtenerEntidadID(id);
            Optional<Empleado> empleado = empleadoServices.obtenerEmpleadoXCiException(ci);
            entidad.get().getPersonal().remove(empleado.get());
            entidadServices.modificarEntidad(entidad.get());
            return ResponseEntity.ok("Trabajador asignado correctamente");
        }catch (Exception e){
            if (e.getMessage().contains("ukgnv576a6jw8egptmch4dph4xx"))
                return ResponseEntity.badRequest().body("Ya fue asignado dicho trabajador a la entidad");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/{id2}")
    @Operation(summary = "Eliminar empleado por id e id",
            description = "Permite eliminar un empleado a una entidad a través del id del centro y el id del empleado")
    public ResponseEntity<?> eliminarEmpleados(@PathVariable(value = "id") Long idEntidad,@PathVariable(value = "id2") Long idEmpleado){
        try {
            Optional<Entidad> entidad = entidadServices.obtenerEntidadID(idEntidad);
            Optional<Empleado> empleado = empleadoServices.obtenerEmpleadoXId(idEmpleado);
            entidad.get().getPersonal().remove(empleado.get());
            entidadServices.modificarEntidad(entidad.get());
            return ResponseEntity.ok("Trabajador asignado correctamente");
        }catch (Exception e){
            if (e.getMessage().contains("ukgnv576a6jw8egptmch4dph4xx"))
                return ResponseEntity.badRequest().body("Ya fue asignado dicho trabajador a la entidad");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
