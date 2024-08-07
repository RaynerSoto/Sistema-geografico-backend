package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.EntidadDto;
import cu.edu.cujae.gestion.core.mapping.Entidad;
import cu.edu.cujae.gestion.core.mapping.Municipio;
import cu.edu.cujae.gestion.core.mapping.Provincia;
import cu.edu.cujae.gestion.core.servicesInterfaces.EntidadServicesInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.MunicipioServicesInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.ProvinciaServiceInterfaces;
import cu.edu.cujae.gestion.core.utils.Validacion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/entidad",name = "Controller para las entidades")
@Tag(name = "Controllador de la entidad"
        ,description = "Es el responsable de controllar todo lo referente con las entidades del sistema")
public class EntidadController {

    private final EntidadServicesInterfaces entidadServices;
    private final ProvinciaServiceInterfaces provinciaServices;
    private final MunicipioServicesInterfaces municipioServices;

    @Autowired
    public EntidadController(EntidadServicesInterfaces entidadServices, ProvinciaServiceInterfaces provinciaServices, MunicipioServicesInterfaces municipioServices) {
        this.entidadServices = entidadServices;
        this.provinciaServices = provinciaServices;
        this.municipioServices = municipioServices;
    }

    @GetMapping("/")
    @Operation(description = "Permite listar todas las entidades del sistema",
    summary = "Listar Entidades")
    public ResponseEntity<?> listadoEntidades(){
        try {
            List<EntidadDto> entidades = entidadServices.listarEntidad().stream().map(EntidadDto::new).toList();
            return ResponseEntity.ok(entidades);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Error en cargar el listado de entidades");
        }
    }

    @PostMapping("/")
    @Operation(summary = "Insertar Entidad",
    description = "Permite insertar una entidad")
    public ResponseEntity<?> insertarEntidad(@RequestBody EntidadDto entidad){
        try {
            entidadServices.existeEntidadNombre(entidad.getNombre());
            Validacion.validarUnsupportedOperationException(entidad);
            Optional<Provincia> provincia = provinciaServices.buscarProvinciaPorNombre(entidad.getProvincia());
            Optional<Municipio> municipio = municipioServices.obtenerMunicipioNombre(entidad.getMunicipio());
            if (!municipioServices.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
                return ResponseEntity.badRequest().body("Este municipio no pertenece a la provincia");
            entidadServices.insertarEntidad(new Entidad(entidad,municipio.get(),provincia.get()));
            return ResponseEntity.ok("Entidad insertada");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar entidad",
    description = "Permite modificar un usuario a través de su ID")
    public ResponseEntity<?> modificarEntidad(@RequestBody EntidadDto entidad,@PathVariable Long id){
        try {
            entidad.setUuid(id);
            entidadServices.existeEntidadNombreNotId(entidad.getNombre(),entidad.getUuid());
            Validacion.validarUnsupportedOperationException(entidad);
            Optional<Provincia> provincia = provinciaServices.buscarProvinciaPorNombre(entidad.getProvincia());
            Optional<Municipio> municipio = municipioServices.obtenerMunicipioNombre(entidad.getMunicipio());
            if (!municipioServices.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
                return ResponseEntity.badRequest().body("Este municipio no pertenece a la provincia");
            entidadServices.modificarEntidad(new Entidad(entidad,municipio.get(),provincia.get()));
            return ResponseEntity.ok("Usuario modificado con éxito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar entidad",
    description = "Permite eliminar entidades a través de su ID")
    public ResponseEntity<?> eliminarEntidad(@PathVariable Long id){
        try {
            entidadServices.eliminarEntidad(id);
            return ResponseEntity.ok("Usuario eliminado con éxito");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
