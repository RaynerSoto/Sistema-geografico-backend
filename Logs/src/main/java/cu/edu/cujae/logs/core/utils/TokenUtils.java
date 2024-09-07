package cu.edu.cujae.logs.core.utils;

import cu.edu.cujae.logs.core.dto.TokenDto;
import cu.edu.cujae.logs.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.logs.core.security.TokenService;
import cu.edu.cujae.logs.core.services.UsuarioServiceInterfaces;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenUtils {
    @Autowired
    private UsuarioServiceInterfaces usuarioService;
    @Autowired
    private TokenService tokenService;

    public static TokenDto requestToken(HttpServletRequest request){
        return new TokenDto(request.getHeader("Authorization"));
    }

    public static TokenDto requestTokenClean(TokenDto token){
        token.setToken(token.getToken().replace("Bearer ", "").replaceAll(" ","").replaceAll("\"",""));
        return token;
    }

    public static TokenDto requestTokenCleanHttp(HttpServletRequest request){
        return new TokenDto(request.getHeader("Authorization").replace("Bearer ", "").replaceAll(" ","").replaceAll("\"",""));
    }

    public String usernameToken(HttpServletRequest request){
        try {
            TokenDto token = requestTokenCleanHttp(request);
            var user = tokenService.getSubjetc(token.getToken());
            return user.toString();
        }catch(Exception e){
            return null;
        }
    }

    public UsuarioDto userToken(HttpServletRequest request){
       try{
           return new UsuarioDto(usuarioService.buscarUsuarioPorUsernameActivo(usernameToken(request)));
       }catch (Exception e){
           return null;
       }
    }
}
