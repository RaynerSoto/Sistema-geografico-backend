package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.Generic;
import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.dto.SexoDto;
import cu.edu.cujae.logs.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.logs.core.mapper.Sexo;
import cu.edu.cujae.logs.core.mapper.Usuario;
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

import java.util.ArrayList;
import java.util.List;

// Revisado todo funcional all 100%
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
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body(sexos);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se ha podido obtener el listado de sexos, compruebe su conexiòn a la base de datos o contacto con el servicio tècnico");
        }
    }

    @PreAuthorize(value = "hasAnyRole('Super Administrador')")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },summary = "Devuelve el listados de los sexos con sus usuarios")
    @GetMapping("/sexosUsuariosList")
    public ResponseEntity<?> listarSexosUsuariosList(HttpServletRequest request) {
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Obtener el listados de los sexos con sus usuarios");
        try {
            List<Generic> generics = new ArrayList<>();
            List<Sexo> sexos = sexoService.listarSexos().stream().toList();
            for (Sexo sexo : sexos){
                generics.add(new Generic(new SexoDto(sexo),sexo.getUsuarioList().stream().map(UsuarioDto::new).toList()));
            }
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado",null);
            return ResponseEntity.ok().body(generics);
        }
        catch (Exception e) {
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado",e.getMessage());
            return ResponseEntity.badRequest().body("No se ha podido obtener el reporte, compruebe su conexiòn a la base de datos o contacto con el servicio tècnico");
        }
    }

}
