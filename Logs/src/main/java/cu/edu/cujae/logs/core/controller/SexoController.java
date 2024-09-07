package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.dto.SexoDto;
import cu.edu.cujae.logs.core.services.EstadoServiceInterfaces;
import cu.edu.cujae.logs.core.services.RegistroServiceInterfaces;
import cu.edu.cujae.logs.core.services.SexoServiceInterfaces;
import cu.edu.cujae.logs.core.utils.RegistroUtils;
import cu.edu.cujae.logs.core.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/login/sexo")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controllador de los sexos", description = "Controlla los sexos admitidos por el sistema")
public class SexoController {

    private final SexoServiceInterfaces sexoService;
    private final RegistroServiceInterfaces registroService;
    private final EstadoServiceInterfaces estadoService;
    private final RegistroUtils registroUtils;
    private final TokenUtils tokenUtils;

    @Autowired
    public SexoController(SexoServiceInterfaces sexoService, RegistroServiceInterfaces registroService, EstadoServiceInterfaces estadoService, RegistroUtils registroUtils, TokenUtils tokenUtils) {
        this.sexoService = sexoService;
        this.registroService = registroService;
        this.estadoService = estadoService;
        this.registroUtils = registroUtils;
        this.tokenUtils = tokenUtils;
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador','Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Devuelve el listados de los sexos")
    @GetMapping("/")
    public ResponseEntity<?> listarSexos(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listado completo de los sexos");
        try {
            List<SexoDto> sexos = sexoService.listarSexos().stream().map(SexoDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok().body(sexos);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
