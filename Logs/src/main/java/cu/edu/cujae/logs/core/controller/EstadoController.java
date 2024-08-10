package cu.edu.cujae.logs.core.controller;
import cu.edu.cujae.logs.core.dto.EstadoDto;
import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.servicesInterfaces.EstadoServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.RegistroServiceInterfaces;
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
@RequestMapping(value = "/api/v1/login/estado")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Controllador de los estados", description = "Controlla los estados de las peticiones que quedan registradas por los usuarios")
public class EstadoController {

    private final EstadoServiceInterfaces estadoService;
    private final RegistroServiceInterfaces registroService;
    private final RegistroUtils registroUtils;
    private final TokenUtils tokenUtils;

    @Autowired
    public EstadoController(EstadoServiceInterfaces estadoService, RegistroServiceInterfaces registroService,RegistroUtils registroUtils,TokenUtils tokenUtils){
        this.estadoService = estadoService;
        this.registroService = registroService;
        this.registroUtils = registroUtils;
        this.tokenUtils = tokenUtils;
    }


    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") }
    ,summary = "Encargado de listar los estados de la aplicación que será retornados al usuario")
    @GetMapping("/")
    public ResponseEntity<?> listarEstados(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Listado de estados del sistema");
        try {
            List<EstadoDto> estados = estadoService.listarEstados().stream().map(EstadoDto::new).toList();
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok().body(estados);
        }
        catch (Exception e){
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
