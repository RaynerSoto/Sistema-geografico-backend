package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.mapping.Privilegio;

import java.util.List;
import java.util.Optional;

public interface PrivilegioServiceInterfaces {
    public void iniciar();

    public List<Privilegio> listarPrivilegios();

    public Optional<Privilegio> obtenerPrivilegio(String nombre) throws Exception;
}
