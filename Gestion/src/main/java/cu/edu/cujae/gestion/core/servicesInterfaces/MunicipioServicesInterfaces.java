package cu.edu.cujae.gestion.core.servicesInterfaces;

import cu.edu.cujae.gestion.core.mapping.Municipio;

import java.util.List;
import java.util.Optional;

public interface MunicipioServicesInterfaces {
    public List<Municipio> listadoMunicipios();

    public Optional<Municipio> obtenerMunicipioNombre(String nombre) throws Exception;

    public boolean isMuncipioinProvincia(String provincia,String municipio) throws Exception;
}
