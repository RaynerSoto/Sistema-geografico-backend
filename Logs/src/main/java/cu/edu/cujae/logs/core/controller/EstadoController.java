package cu.edu.cujae.logs.core.controller;
import cu.edu.cujae.logs.core.dto.EstadoDto;
import cu.edu.cujae.logs.core.servicesInterfaces.EstadoServiceInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/estado")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controllador de los estados", description = "Controlla los estados de las peticiones que quedan registradas por los usuarios")
public class EstadoController {
    @Autowired
    private EstadoServiceInterfaces estadoService;

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Encargado de listar los estados de la aplicación que será retornados al usuario")
    @GetMapping("/")
    public ResponseEntity<?> listarEstados() {
        try {
            return ResponseEntity.ok().body(estadoService.listarEstados().stream().map(EstadoDto::new).toList());
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
