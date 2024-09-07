package cu.edu.cujae.gestion.core.security;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // Obtener el HttpServletRequest desde RequestContextHolder
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        if (request != null) {
            String clientName = template.feignTarget().name();
            if ("Registros".equals(clientName)) {
                String token = request.getHeader("Authorization");
                template.header("TokenVariable", token);
            }
        }
    }
}
