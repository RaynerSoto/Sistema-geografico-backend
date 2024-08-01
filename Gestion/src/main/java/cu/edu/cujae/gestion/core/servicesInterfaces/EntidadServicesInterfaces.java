package cu.edu.cujae.gestion.core.servicesInterfaces;

import cu.edu.cujae.gestion.core.mapping.Entidad;

import java.util.List;
import java.util.Optional;

public interface EntidadServicesInterfaces {
    public void insertarEntidad(Entidad entidad);

    public void modificarEntidad(Entidad entidad) throws Exception;

    public void eliminarEntidad(Long id) throws Exception;

    public List<Entidad> listarEntidad();

    public void existeEntidadNombre(String nombre) throws Exception;

    public void existeEntidadNombreNotId(String nombre, Long id) throws Exception;

    public Optional<Entidad> obtenerEntidadNombre(String nombre) throws Exception;
}
