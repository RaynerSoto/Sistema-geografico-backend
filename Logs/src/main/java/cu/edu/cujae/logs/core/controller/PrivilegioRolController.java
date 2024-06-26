package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.PrivilegioRolDto;
import cu.edu.cujae.logs.core.mapping.Privilegio;
import cu.edu.cujae.logs.core.mapping.PrivilegioRol;
import cu.edu.cujae.logs.core.mapping.Rol;
import cu.edu.cujae.logs.core.repository.PrivilegioRolRepository;
import cu.edu.cujae.logs.core.services.PrivilegioRolService;
import cu.edu.cujae.logs.core.servicesInterfaces.PrivilegioRolServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.PrivilegioServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/privilegioRol")
public class PrivilegioRolController {
    @Autowired
    private PrivilegioRolRepository privilegioRolRepository;

    @Autowired
    private PrivilegioRolServiceInterfaces privilegioRolService;

    @Autowired
    private RolServiceInterfaces rolService;

    @Autowired
    private PrivilegioServiceInterfaces privilegioService;


    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody PrivilegioRolDto privilegioRolDto) {
        try {
            Optional<Rol> rol = rolService.consultarRol(privilegioRolDto.getRol());
            Optional<Privilegio> privilegio = privilegioService.obtenerPrivilegio(privilegioRolDto.getCodigo());
            if (privilegioRolRepository.findByRolEqualsAndPrivilegioCodigoEquals(rol.get(),privilegio.get()).isPresent() == false){
                privilegioRolService.addPrivilegioRol(new PrivilegioRol(rol.get(),privilegio.get()));
                return ResponseEntity.ok("Asignado al rol el privilegio");
            }else {
                return ResponseEntity.badRequest().body("Rol que ya cuenta con dicho privilegio");
            }

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
