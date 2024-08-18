package cu.edu.cujae.gestion.core.feignclient;

import cu.edu.cujae.gestion.core.dto.RegistroDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "Logs")
public interface RegistroServiceInterfaces {

    @PostMapping(path = "/api/v1/registro/")
    public void insertarRegistro(RegistroDto registro);
}
