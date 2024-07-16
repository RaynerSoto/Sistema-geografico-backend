package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.SexoDto;
import cu.edu.cujae.logs.core.servicesInterfaces.SexoServiceInterfaces;
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
@RequestMapping("/api/v1/sexo")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controllador de los sexos", description = "Controlla los sexos admitidos por el sistema")
public class SexoController {
    @Autowired
    private SexoServiceInterfaces sexoService;

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Devuelve el listados de los sexos")
    @GetMapping("/")
    public ResponseEntity<?> listarSexos() {
        try {
            return ResponseEntity.ok().body(sexoService.listarSexos().stream().map(SexoDto::new).toList());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
