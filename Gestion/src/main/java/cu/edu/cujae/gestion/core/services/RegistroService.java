package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.dto.RegistroDto;
import cu.edu.cujae.gestion.core.feignclient.RegistroServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

    public final RegistroServiceInterfaces registroServiceInterfaces;

    @Autowired
    public RegistroService(RegistroServiceInterfaces registroServiceInterfaces) {
        this.registroServiceInterfaces = registroServiceInterfaces;
    }

    public void insertarRegistro(RegistroDto registroDto){
        registroServiceInterfaces.insertarRegistro(registroDto);
    }
}
