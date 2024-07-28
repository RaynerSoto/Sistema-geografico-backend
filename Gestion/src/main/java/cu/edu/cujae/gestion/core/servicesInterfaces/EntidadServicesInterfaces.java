package cu.edu.cujae.gestion.core.servicesInterfaces;

import cu.edu.cujae.gestion.core.mapping.Entidad;

import java.util.List;

public interface EntidadServicesInterfaces {
    public void insertarEntidad(Entidad entidad);

    public void modificarEntidad(Entidad entidad);

    public void eliminarEntidad(Long id);

    public List<Entidad> listarEntidad();
}
