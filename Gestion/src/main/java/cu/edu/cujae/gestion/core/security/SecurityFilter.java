package cu.edu.cujae.gestion.core.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.netflix.discovery.converters.Auto;
import cu.edu.cujae.gestion.core.dto.TokenDto;
import cu.edu.cujae.gestion.core.dto.UsuarioDto;
import cu.edu.cujae.gestion.core.feignclient.TokenServiceInterfaces;
import cu.edu.cujae.gestion.core.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.LinkedHashMap;

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
            throw new ServletException("Error de autorizaciòn");
        }
        else {
            TokenDto tokenDto = TokenUtils.getTokenDto(request);
            if (tokenDto == null) {
                throw new ServletException("Error de autorizaciòn");
            }
            else {
                try {
                    UsuarioDto user = mapper.convertValue(tokenService.tokenExists(tokenDto).getBody(), UsuarioDto.class);
                    if (user == null) {
                        throw new ServletException("Error en el usuario autenticado");
                    }
                    else
                        filterChain.doFilter(request, response);
                }catch (Exception e){
                    throw new ServletException("Error en el autenticado");
                }
            }
        }
    }
}
