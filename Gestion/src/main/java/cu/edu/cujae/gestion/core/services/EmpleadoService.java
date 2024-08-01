package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.exception.SearchException;
import cu.edu.cujae.gestion.core.mapping.Empleado;
import cu.edu.cujae.gestion.core.repository.EmpleadoRepository;
import cu.edu.cujae.gestion.core.servicesInterfaces.EmpleadoServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService implements EmpleadoServiceInterfaces {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> obtenerEmpleados() {
        return empleadoRepository.findAll();
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoXCiException(String ci) throws Exception{
        return Optional.ofNullable(empleadoRepository.findByCiEqualsIgnoreCase(ci)
                .orElseThrow(
                        () -> new SearchException("No se encontro el usuario con ci " + ci)
                ));
    }

    @Override
    public Optional<Empleado> obtenerEmpleadoXCi(String ci){
        return empleadoRepository.findByCiEqualsIgnoreCase(ci);
    }

    @Override
    public void insertarEmpleado(Empleado empleado){
        empleadoRepository.save(empleado);
    }
}
