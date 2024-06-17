package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.mapping.Rol;

import java.util.List;
import java.util.Optional;

public interface RolServiceInterfaces{
    public void iniciar();

    public void insertarRol(Rol rol);

    public List<Rol> consultarRol();

    public void modificarRol(Rol rol, Long id) throws Exception;

    public void eliminarRol(Long id) throws Exception;

    public Optional<Rol> consultarRolID(Long id) throws Exception;

    public Optional<Rol> consultarRol(String rol) throws Exception;

    public Optional<Rol> consultarRolNombre(String rol) throws Exception;
}
