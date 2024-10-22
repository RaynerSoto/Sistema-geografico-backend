package cu.edu.cujae.gestion.core.feignclient;

import cu.edu.cujae.gestion.core.dto.TokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "token",url = "http://localhost:8086")
public interface TokenServiceInterfaces{

    @PostMapping("/login/validarToken")
    public ResponseEntity<?> tokenExists(@RequestBody TokenDto token);

    @PostMapping("/userdetails")
    public ResponseEntity<?> retornoUserDetails(@RequestBody TokenDto tokenDto);
}
