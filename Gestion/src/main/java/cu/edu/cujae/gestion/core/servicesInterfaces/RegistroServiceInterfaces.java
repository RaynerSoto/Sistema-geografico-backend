package cu.edu.cujae.gestion.core.servicesInterfaces;

import cu.edu.cujae.gestion.core.dto.RegistroDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "Logs",url = "http://localhost:8086/api/v1/registro")
public interface RegistroServiceInterfaces {

    @PostMapping("/")
    public void insertarRegistro(RegistroDto registro);
}
