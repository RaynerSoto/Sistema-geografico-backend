package cu.edu.cujae.logs.core.security;

import cu.edu.cujae.logs.core.dto.TokenDto;
import cu.edu.cujae.logs.core.dto.UsuarioDto;
import cu.edu.cujae.logs.core.dto.UsuarioLoginDto;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;
import java.util.Optional;

@RestController
@RequestMapping("/login")
@Tag(name = "Controlador de la autentificación",description = "Determina el funcionamiento de la seguridad")
public class AutentificacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/")
    @Operation(summary = "Encargado de autentificar el usuario")
    public ResponseEntity<?> autenticacionUsuario(@RequestBody UsuarioLoginDto usuarioLoginDto, HttpServletRequest httpServletRequest){
        try {
            System.out.println(httpServletRequest.getRemoteHost());//Obtener IP versión V4
            Usuario usuario = usuarioService.obtenerUsuarioPorUsernameAndPassword(usuarioLoginDto.getUsername(), usuarioLoginDto.getPassword());
            usuario = new Usuario(usuario, usuarioLoginDto.getPassword());
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(),usuario.getPassword());;
            var usuarioAutentificado = authenticationManager.authenticate(authenticationToken);
            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutentificado.getPrincipal());
            return ResponseEntity.ok(new TokenDto(JWTtoken));
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validarToken")
    @Operation(summary = "Encargado de autentificar a un usuario según su token")
    public ResponseEntity<?> autenticacionUsuario(@RequestBody TokenDto tokenDto){
        try {
            if (tokenDto.getToken().equals(null) || tokenDto.getToken().equals("")) {
                throw new Exception("Token nulo o inválido");
            }
            String nombre = tokenService.getSubjetc(tokenDto.getToken());
            Optional<Usuario> user = Optional.ofNullable(usuarioService.usuarioActivoUsername(nombre).orElseThrow(
                    () -> new RuntimeException("No se encontró al usuario del token")
            ));
            return ResponseEntity.ok(new UsuarioDto(user.get()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
