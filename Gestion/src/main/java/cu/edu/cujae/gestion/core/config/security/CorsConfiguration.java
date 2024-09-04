package cu.edu.cujae.gestion.core.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000", "http://localhost:8080",
                        "http://localhost:8086", "http://localhost:8087",
                        "http://localhost:8088", "http://localhost:8089",
                        "http://192.168.1.1", "http://192.168.1.104"
                        ,"http://192.168.1.103","http://192.168.1.102"
                        ,"http://192.168.1.101","http://192.168.1.100",
                        "http://152.207.224.210/","http://152.207.231.197"
                ) // Lista explícita de orígenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
