package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.mapper.Provincia;

import java.util.List;
import java.util.Optional;

public interface ProvinciaServiceInterfaces {
    List<Provincia> listadoProvincia();
    Optional<Provincia> buscarProvinciaPorNombre(String nombre) throws Exception;
}
