package cu.edu.cujae.logs.core.services.servicesimpl;

import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.mapper.Registro;
import cu.edu.cujae.logs.core.repository.RegistroRepository;
import cu.edu.cujae.logs.core.repository.UsuarioRepository;
import cu.edu.cujae.logs.core.services.RegistroServiceInterfaces;
import cu.edu.cujae.logs.core.services.UsuarioServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroService implements RegistroServiceInterfaces {

    private final RegistroRepository registroRepository;
    private final UsuarioServiceInterfaces usuarioService;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public RegistroService(RegistroRepository registroRepository, UsuarioServiceInterfaces usuarioService, UsuarioRepository usuarioRepository) {
        this.registroRepository = registroRepository;
        this.usuarioService = usuarioService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void insertarRegistro(Registro registro) {
        registroRepository.save(registro);
    }

    @Override
    public List<Registro> listarRegistros() {
        return registroRepository.findAll();
    }

    @Override
    public List<Registro> listarRegistrosPoUsuario(Long usuario) {
        return registroRepository.findAll().stream().filter(s -> s.getPotentialUserId() == usuario).toList();
    }

    @Override
    public List<RegistroDto> listadoRegistros() {
        return registroRepository.findAll().stream()
                .map(registro -> {
                    return new RegistroDto(registro,usuarioService.buscarUsuarioReturnedNull(registro.getPotentialUserId()));
                }).toList();
    }
}
