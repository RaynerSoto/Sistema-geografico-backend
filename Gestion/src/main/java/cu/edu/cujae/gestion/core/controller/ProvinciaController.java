package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.ProvinciaDto;
import cu.edu.cujae.gestion.core.dto.RegistroDto;
import cu.edu.cujae.gestion.core.services.RegistroService;
import cu.edu.cujae.gestion.core.servicesInterfaces.ProvinciaServiceInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gestion/provincia")
@Tag(name = "Controllador de provincias",
        description = "Controllador encargado de todo lo relacionado con las provincias del país")
public class ProvinciaController {

    private final ProvinciaServiceInterfaces provinciaService;
    private final RegistroService registroService;

    @Autowired
    public ProvinciaController(ProvinciaServiceInterfaces provinciaService,RegistroService registroService) {
        this.provinciaService = provinciaService;
        this.registroService = registroService;
    }

    @GetMapping("/")
    @Operation(summary = "Listado de provincias",
            description = "Permite obtener el listado de todos las provincias del sistema")
    public ResponseEntity<?> getAllProvincia() {
        RegistroDto registroDto = new RegistroDto("Obtener todas las provincias");
        registroDto.setUsuario("Rayner");
        try {
            List<ProvinciaDto> pronvincias = provinciaService.listadoProvincia().stream()
                    .map(ProvinciaDto::new)
                    .sorted(Comparator.comparing(ProvinciaDto::getUuid))
                    .toList();
            try {
                registroDto.setEstado("Aceptado");
                registroDto.setIp("127.0.0.1");
                registroService.insertarRegistro(registroDto);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return ResponseEntity.ok(pronvincias);
        }catch (Exception e){
            try {
                registroDto.setEstado("Rechazado");
                registroDto.setIp("127.0.0.1");
                registroService.insertarRegistro(registroDto);
            }catch (Exception e1){
                System.out.println(e1.getMessage());
            }
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/nombre")
    @Operation(summary = "Obtener provincias por nombre",
            description = "Obtener todos los datos de la provincia según su nombre")
    public ResponseEntity<?> getProvinciaNombre(String nombre) {
        try {
            return ResponseEntity.ok(new ProvinciaDto(provinciaService.buscarProvinciaPorNombre(nombre)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
