package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.servicesInterfaces.UsuarioServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/autentificacion")
public class AutentificacionController{
    @Autowired
    private UsuarioServiceInterfaces usuarioService;

}
