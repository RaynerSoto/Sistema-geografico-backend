package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.exception.SearchException;
import cu.edu.cujae.gestion.core.mapping.Municipio;
import cu.edu.cujae.gestion.core.repository.MunicipioRepository;
import cu.edu.cujae.gestion.core.servicesInterfaces.MunicipioServicesInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService implements MunicipioServicesInterfaces {

    private final MunicipioRepository municipioRepository;

    @Autowired
    public MunicipioService(MunicipioRepository municipioRepository) {
        this.municipioRepository = municipioRepository;
    }

    @Override
    public List<Municipio> listadoMunicipios() {
        return municipioRepository.findAll();
    }

    @Override
    public Optional<Municipio> obtenerMunicipioNombre(String nombre) throws Exception{
        return Optional.ofNullable(municipioRepository.findByNombreEqualsIgnoreCase(nombre).
                orElseThrow(() -> new SearchException("Municipio no encontrado")));
    }

    @Override
    public boolean isMuncipioinProvincia(String provincia, String municipioNombre) throws Exception {
        Municipio municipio = obtenerMunicipioNombre(municipioNombre).get();
        if (municipio.getProvincia().getNombre().equalsIgnoreCase(provincia)) {
            return true;
        }
        return false;
    }
}
