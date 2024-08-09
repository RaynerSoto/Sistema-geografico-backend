package cu.edu.cujae.gestion.core.security;

import cu.edu.cujae.gestion.core.utils.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
public class SecurityFilter extends OncePerRequestFilter {
    private TokenUtils tokenUtils;

    @Autowired
    public SecurityFilter(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //String token = TokenUtils.requestTokenClean(request);
        //if (token == null)
            //throw new ServletException("Token no encontrado");
        filterChain.doFilter(request, response);
    }
}
