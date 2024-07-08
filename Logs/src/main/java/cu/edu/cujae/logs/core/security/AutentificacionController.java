package cu.edu.cujae.logs.core.security;

import cu.edu.cujae.logs.core.dto.UsuarioLoginDto;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class AutentificacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/")
    public ResponseEntity<?> autenticacionUsuario(@RequestBody UsuarioLoginDto usuarioLoginDto){
        try {
            Usuario usuario = usuarioService.obtenerUsuarioPorUsernameAndPassword(usuarioLoginDto.getUsername(), usuarioLoginDto.getPassword());
            usuario = new Usuario(usuario, usuarioLoginDto.getPassword());
            Authentication token = new UsernamePasswordAuthenticationToken(usuario.getUsername(),usuario.getPassword());;
            authenticationManager.authenticate(token);
            return ResponseEntity.ok(token);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}