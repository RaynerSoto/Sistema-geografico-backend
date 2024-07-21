package cu.edu.cujae.logs.core.controller;

import cu.edu.cujae.logs.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.logs.core.mapping.Rol;
import cu.edu.cujae.logs.core.mapping.Sexo;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.SexoServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.UsuarioServiceInterfaces;
import cu.edu.cujae.logs.core.utils.Validacion;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@SpringBootTest
class UsuarioControllerTest {
    @Autowired
    private UsuarioServiceInterfaces usuarioService;
    @Autowired
    private RolServiceInterfaces rolRepository;
    @Autowired
    private SexoServiceInterfaces sexoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void insertarUsuario() throws Exception {
        UsuarioDto usuario = new UsuarioDto();
        usuario.setName("Rayner Alejandro Soto Martínez");
        usuario.setUsername("Rayner");
        usuario.setEmail("rayner@gmail.com");
        usuario.setPassword("12345");
        usuario.setSexo("Masculino");
        usuario.setRol("Super Administrador");
        usuario.setActivo(true);
        Validacion.validarUnsupportedOperationException(usuario);
        Optional<Rol> rol = rolRepository.consultarRol(usuario.getRol());
        Optional<Sexo> sexo = sexoService.consultarSexo(usuario.getSexo());
        usuarioService.validarUsuarioInsertar(usuario.getEmail(),usuario.getUsername());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioService.insertarUsuario(new Usuario(usuario,rol.get(),sexo.get()));
    }
}