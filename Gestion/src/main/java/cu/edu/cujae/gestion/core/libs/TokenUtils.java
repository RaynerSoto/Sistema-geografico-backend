package cu.edu.cujae.gestion.core.libs;
import cu.edu.cujae.gestion.core.dto.TokenDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class TokenUtils {

    public static String requestTokenClean(HttpServletRequest request){
        return request.getHeader("Authorization").replace("Bearer ", "").replaceAll(" ","").replaceAll("\"","");
    }

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

    public static TokenDto getTokenDto(HttpServletRequest request){
       try {
           return new TokenDto(requestTokenClean(request));
       }
       catch (Exception e){
           System.out.println(e.getMessage());
           return null;
       }
    }
}
