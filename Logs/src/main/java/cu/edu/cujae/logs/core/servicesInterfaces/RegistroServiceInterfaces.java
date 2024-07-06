package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.mapping.Registro;

import java.util.List;

public interface RegistroServiceInterfaces {
    void insertarRegistro(Registro registro);

    List<Registro> listarRegistros();

    List<Registro> listarRegistrosPoUsuario(String usuario);
}
