package cu.edu.cujae.logs.core.security;

import com.google.gson.Gson;
import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.dto.TokenDto;
import cu.edu.cujae.logs.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.logs.core.dto.usuario.UsuarioLoginDto;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.services.UsuarioService;
import cu.edu.cujae.logs.core.utils.RegistroUtils;
import cu.edu.cujae.logs.core.utils.TokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private RegistroUtils registroUtils;
    @Autowired
    private TokenUtils tokenUtils;

    @PostMapping(value = "/")
    @Operation(summary = "Encargado de autentificar el usuario")
    public ResponseEntity<?> autenticacionUsuario(@RequestBody UsuarioLoginDto usuarioLoginDto, HttpServletRequest request){
        RegistroDto registroDto = registroUtils.registroHttpUtils(request,"Autenticación de usuario: "+usuarioLoginDto.getUsername());
        try {
            System.out.println("Registro");
            System.out.println(request.getRemoteHost());//Obtener IP versión V4
            Usuario usuario = usuarioService.obtenerUsuarioPorUsernameAndPassword(usuarioLoginDto.getUsername(), usuarioLoginDto.getPassword());
            usuario = new Usuario(usuario, usuarioLoginDto.getPassword());
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(),usuario.getPassword());;
            var usuarioAutentificado = authenticationManager.authenticate(authenticationToken);
            var JWTtoken = tokenService.generarToken((Usuario) usuarioAutentificado.getPrincipal());
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Aceptado");
            return ResponseEntity.ok(new TokenDto(JWTtoken));
        }catch (Exception e){
            System.out.println(e);
            registroUtils.insertarRegistros(registroDto,tokenUtils.userToken(request),"Rechazado");
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
