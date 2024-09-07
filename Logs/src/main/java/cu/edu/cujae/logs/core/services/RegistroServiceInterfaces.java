package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.mapper.Registro;

import java.util.List;

public interface RegistroServiceInterfaces {
    void insertarRegistro(Registro registro);

    List<Registro> listarRegistros();

    List<Registro> listarRegistrosPoUsuario(Long usuario);

    List<RegistroDto> listadoRegistros();
}
