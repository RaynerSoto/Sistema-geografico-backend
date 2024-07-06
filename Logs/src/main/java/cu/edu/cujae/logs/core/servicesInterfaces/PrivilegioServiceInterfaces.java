package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.mapping.Privilegio;

import java.util.List;
import java.util.Optional;

public interface PrivilegioServiceInterfaces {
    void iniciar();

    List<Privilegio> listarPrivilegios();

    Optional<Privilegio> obtenerPrivilegio(String nombre) throws Exception;
}
