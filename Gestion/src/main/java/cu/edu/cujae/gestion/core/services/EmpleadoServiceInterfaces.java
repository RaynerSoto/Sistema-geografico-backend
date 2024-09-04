package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.model.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoServiceInterfaces {
    List<Empleado> obtenerEmpleados();

    Optional<Empleado> obtenerEmpleadoXCiException(String ci) throws Exception;

    Optional<Empleado> obtenerEmpleadoXCi(String ci);

    void insertarEmpleado(Empleado empleado);

    Optional<Empleado> obtenerEmpleadoXId(Long id) throws Exception;

    void modificarEmpleado(Empleado empleado) throws Exception;

    void eliminarEmpleado(Long id) throws Exception;

    void eliminarEmpleado(String ci) throws Exception;
}
