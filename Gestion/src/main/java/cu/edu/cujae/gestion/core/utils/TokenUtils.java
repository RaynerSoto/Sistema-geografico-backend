package cu.edu.cujae.gestion.core.utils;
import cu.edu.cujae.gestion.core.dto.TokenDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class TokenUtils {

    public static String requestTokenClean(HttpServletRequest request){
        return request.getHeader("Authorization").replace("Bearer ", "").replaceAll(" ","").replaceAll("\"","");
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
