package cu.edu.cujae.gestion.core.services.servicesImpl;

import cu.edu.cujae.gestion.core.dto.RegistroDto;
import cu.edu.cujae.gestion.core.feignclient.RegistroServiceInterfaces;
import cu.edu.cujae.gestion.core.services.RegistroServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroService implements RegistroServiceInterface {

    public final RegistroServiceInterfaces registroServiceInterfaces;

    @Autowired
    public RegistroService(RegistroServiceInterfaces registroServiceInterfaces) {
        this.registroServiceInterfaces = registroServiceInterfaces;
    }

    public void insertarRegistro(RegistroDto registroDto,String username){
        registroServiceInterfaces.insertarRegistro(registroDto,username,"localhost",8087);
    }
}
