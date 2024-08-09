package cu.edu.cujae.gestion.core.utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class TokenUtils {

    public static String requestTokenClean(HttpServletRequest request){
        return request.getHeader("Authorization").replace("Bearer ", "").replaceAll(" ","").replaceAll("\"","");
    }
}
