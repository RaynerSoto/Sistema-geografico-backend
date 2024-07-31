package cu.edu.cujae.gestion.core.servicesInterfaces;

import cu.edu.cujae.gestion.core.mapping.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoServiceInterfaces {
    public List<Empleado> obtenerEmpleados();

    public Optional<Empleado> obtenerUsuarioXCiException(String ci) throws Exception;

    public Optional<Empleado> obtenerUsuarioXCi(String ci);
}
