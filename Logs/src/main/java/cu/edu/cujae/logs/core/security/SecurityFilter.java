package cu.edu.cujae.logs.core.security;

import cu.edu.cujae.logs.core.dto.TokenDto;
import cu.edu.cujae.logs.core.repository.UsuarioRepository;
import cu.edu.cujae.logs.core.utils.TokenUtils;
import cu.edu.cujae.logs.core.utils.Validacion;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Inicio doFilter");
        TokenDto token = TokenUtils.requestToken(request);
        String requesUri = request.getRequestURI();
        System.out.println(requesUri);
        if (token.getToken() != null){
            Validacion.validarUnsupportedOperationException(token);
            token.setToken(token.getToken().replace("Bearer ", "").replaceAll(" ","").replaceAll("\"",""));
            var user = tokenService.getSubjetc(token.getToken());
            System.out.println(token);
            if(user != null){
                var usuario = usuarioRepository.findByUsernameEqualsIgnoreCase(user);
                //Forzar inicio de sesión
                System.out.println(usuario.getAuthorities());
                var autenticacion = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticacion);
            }
            else {
                throw new Exception("Usuario no encontrado");
            }
        }
        filterChain.doFilter(request, response);
    }
}
