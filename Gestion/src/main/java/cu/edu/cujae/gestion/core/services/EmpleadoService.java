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

    private final EmpleadoRepository empleadoRepository;

    @Autowired
    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

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

    @Override
    public Optional<Empleado> obtenerEmpleadoXId(Long id) throws Exception{
        return Optional.ofNullable(empleadoRepository.findById(id).orElseThrow(
                () -> new SearchException("No existe un empleado con dicho ID")
        ));
    }

    @Override
    public void modificarEmpleado(Empleado empleado) throws Exception {
        if (empleadoRepository.existsById(empleado.getUuid()))
            empleadoRepository.save(empleado);
        else{
            throw new SearchException("No se encuentra el empleado a modiicar");
        }
    }

    @Override
    public void eliminarEmpleado(Long id) throws Exception {
        if (empleadoRepository.existsById(id))
            empleadoRepository.deleteById(id);
        else{
            throw new SearchException("No se encuentra el empleado a eliminar");
        }
    }

    @Override
    public void eliminarEmpleado(String ci) throws Exception{
        if (empleadoRepository.existsByCiEqualsIgnoreCase(ci))
            empleadoRepository.delete(empleadoRepository.findByCiEqualsIgnoreCase(ci).get());
        else{
            throw new SearchException("No se encuentra el empleado a eliminar");
        }
    }
}
