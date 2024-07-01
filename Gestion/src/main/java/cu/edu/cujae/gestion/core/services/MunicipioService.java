package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.mapping.Municipio;
import cu.edu.cujae.gestion.core.repository.MunicipioRepository;
import cu.edu.cujae.gestion.core.servicesInterfaces.MunicipioServicesInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService implements MunicipioServicesInterfaces {
    @Autowired
    private MunicipioRepository municipioRepository;


    @Override
    public List<Municipio> listadoMunicipios() {
        return municipioRepository.findAll();
    }
}
