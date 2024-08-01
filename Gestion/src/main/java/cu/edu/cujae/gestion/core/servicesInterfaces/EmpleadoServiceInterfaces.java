package cu.edu.cujae.gestion.core.servicesInterfaces;

import cu.edu.cujae.gestion.core.mapping.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoServiceInterfaces {
    public List<Empleado> obtenerEmpleados();

    public Optional<Empleado> obtenerEmpleadoXCiException(String ci) throws Exception;

    public Optional<Empleado> obtenerEmpleadoXCi(String ci);

    void insertarEmpleado(Empleado empleado);
}
