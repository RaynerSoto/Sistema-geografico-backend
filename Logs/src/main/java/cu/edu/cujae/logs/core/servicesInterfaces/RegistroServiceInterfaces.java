package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.mapping.Registro;

import java.util.List;

public interface RegistroServiceInterfaces {
    public void insertarRegistro(Registro registro);

    public List<Registro> listarRegistros();

    public List<Registro> listarRegistrosPoUsuario(String usuario);
}
