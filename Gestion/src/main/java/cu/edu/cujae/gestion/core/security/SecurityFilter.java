package cu.edu.cujae.gestion.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import cu.edu.cujae.gestion.core.dto.TokenDto;
import cu.edu.cujae.gestion.core.feignclient.TokenServiceInterfaces;
import cu.edu.cujae.gestion.core.dto.UsuarioDto;
import cu.edu.cujae.gestion.core.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    public final TokenServiceInterfaces tokenService;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public SecurityFilter(TokenServiceInterfaces tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader("Authorization") == null) {
            filterChain.doFilter(request,response);
        }
        else {
            TokenDto tokenDto = TokenUtils.getTokenDto(request);
            if (tokenDto == null) {
                throw new ServletException("Error de autorizaciòn");
            }
            else {
                try {
                    cu.edu.cujae.gestion.core.dto.UsuarioDto user = mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), cu.edu.cujae.gestion.core.dto.UsuarioDto.class);
                    if (user == null) {
                        throw new ServletException("Error en el usuario autenticado");
                    }
                    else{
                        UsuarioDto usuarioDto = new UsuarioDto(user);
                        var autenticacion = new UsernamePasswordAuthenticationToken(usuarioDto,null, usuarioDto.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(autenticacion);
                        filterChain.doFilter(request, response);
                    }
                }catch (Exception e){
                    throw new ServletException("Error en el autenticado");
                }
            }
        }
    }
}
