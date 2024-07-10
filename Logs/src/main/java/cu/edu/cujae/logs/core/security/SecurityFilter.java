package cu.edu.cujae.logs.core.security;

import cu.edu.cujae.logs.core.dto.TokenDto;
import cu.edu.cujae.logs.core.utils.Validacion;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Inicio doFilter");
        TokenDto token = new TokenDto(request.getHeader("Authorization"));
        String requesUri = request.getRequestURI();
        System.out.println(requesUri);
        if (token.getToken() != null){
            Validacion.validarUnsupportedOperationException(token);
            token.setToken(token.getToken().replace("Bearer ", ""));
            var valor = tokenService.getSubjetc(token.getToken());
            System.out.println(token);
        }
        try {
            filterChain.doFilter(request, response);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
