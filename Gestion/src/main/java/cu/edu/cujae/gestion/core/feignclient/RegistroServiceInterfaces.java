package cu.edu.cujae.gestion.core.feignclient;

import cu.edu.cujae.gestion.core.dto.RegistroDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Registros",url = "http://localhost:8086")
public interface RegistroServiceInterfaces {

    @PostMapping(path = "/api/v1/login/registro/")
    public void insertarRegistro(@RequestBody RegistroDto registro,@RequestHeader String username);
}
