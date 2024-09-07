package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.mapper.Rol;

import java.util.List;
import java.util.Optional;

public interface RolServiceInterfaces{
    void iniciar();

    void insertarRol(Rol rol);

    List<Rol> consultarRol();

    void modificarRol(Rol rol, Long id) throws Exception;

    void eliminarRol(Long id) throws Exception;

    Optional<Rol> consultarRolID(Long id) throws Exception;

    Optional<Rol> consultarRol(String rol) throws Exception;

    Optional<Rol> consultarRolNombre(String rol) throws Exception;
}
