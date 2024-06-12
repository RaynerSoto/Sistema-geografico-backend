package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.clases.Rol;

import java.util.List;

public interface RolServiceInterfaces{
    public void insertarRol(Rol rol);

    public List<Rol> consultarRol();
}
