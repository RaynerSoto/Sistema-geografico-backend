package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.mapper.Entidad;

import java.util.List;
import java.util.Optional;

public interface EntidadServicesInterfaces {
    void insertarEntidad(Entidad entidad);

    void modificarEntidad(Entidad entidad) throws Exception;

    void eliminarEntidad(Long id) throws Exception;

    List<Entidad> listarEntidad();

    void existeEntidadNombre(String nombre) throws Exception;

    void existeEntidadNombreNotId(String nombre, Long id) throws Exception;

    Optional<Entidad> obtenerEntidadNombre(String nombre) throws Exception;

    Optional<Entidad> obtenerEntidadID(Long id) throws Exception;
}
