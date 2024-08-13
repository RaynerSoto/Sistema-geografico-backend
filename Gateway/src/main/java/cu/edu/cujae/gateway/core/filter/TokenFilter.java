package cu.edu.cujae.gateway.core.filter;


import cu.edu.cujae.gateway.core.dto.TokenDto;
import lombok.SneakyThrows;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class TokenFilter extends AbstractGatewayFilterFactory<TokenFilter.Config> {

    public TokenFilter(){
        super(Config.class);
    }

    @SneakyThrows
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            try {
                ServerHttpRequest request = exchange.getRequest();
                TokenDto tokenDto = new TokenDto(request.getHeaders().getFirst("Authorization"));
                if(tokenDto.getToken().isEmpty() ||tokenDto.getToken().isBlank() || tokenDto == null || tokenDto.getToken() == null){
                    return badRespuesta(exchange);
                }
                return chain.filter(exchange);
            }catch (Exception e){
                return badRespuesta(exchange);
            }
        };
    }

    public Mono<Void> badRespuesta(ServerWebExchange exchange){
        byte[] bytes = "{\"error\": \"Acceso denegado\"}".getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        exchange.getResponse().getHeaders().add("Content-Type", "application/json");
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    public static class Config{

    }
}