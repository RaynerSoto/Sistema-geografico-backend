package cu.edu.cujae.gestion.core.utils;
import cu.edu.cujae.gestion.core.dto.RegistroDto;
import cu.edu.cujae.gestion.core.services.servicesImpl.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroUtils {
    public static RegistroService registroService;

    @Autowired
    public RegistroUtils(RegistroService registroService) {
        this.registroService = registroService;
    }

    public void insertarRegistro(String username, String actividad, String ip, String estado,String mensaje){
        registroService.insertarRegistro(new RegistroDto(username, actividad,ip,estado,mensaje),username);
    }
}
