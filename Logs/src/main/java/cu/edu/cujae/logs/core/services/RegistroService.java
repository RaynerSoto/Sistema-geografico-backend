package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.mapping.Registro;
import cu.edu.cujae.logs.core.repository.RegistroRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.RegistroServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroService implements RegistroServiceInterfaces {

    @Autowired
    private RegistroRepository registroRepository;

    @Override
    public void insertarRegistro(Registro registro) {
        registroRepository.save(registro);
    }

    @Override
    public List<Registro> listarRegistros() {
        return registroRepository.findAll();
    }

    @Override
    public List<Registro> listarRegistrosPoUsuario(String usuario) {
        return registroRepository.findAll().stream().filter(s -> s.getNombreUsuario().getUsername().equalsIgnoreCase(usuario)).toList();
    }
}
