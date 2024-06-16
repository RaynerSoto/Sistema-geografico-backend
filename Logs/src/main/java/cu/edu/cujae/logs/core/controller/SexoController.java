package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.SexoDto;
import cu.edu.cujae.logs.core.servicesInterfaces.SexoServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sexo")
public class SexoController {
    @Autowired
    private SexoServiceInterfaces sexoService;

    @GetMapping("/")
    public ResponseEntity<?> listarSexos() {
        try {
            List<SexoDto> sexos = sexoService.listarSexos().stream().map(s-> new SexoDto(s)).toList();
            return ResponseEntity.ok().body(sexos);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
