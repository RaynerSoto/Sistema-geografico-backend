package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.mapping.ZonaTransporte;
import cu.edu.cujae.gestion.core.repository.ZonaTransporteRepository;
import cu.edu.cujae.gestion.core.servicesInterfaces.ZonaTransporteServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZonaTransporteService implements ZonaTransporteServiceInterfaces {

    @Autowired
    private ZonaTransporteRepository zonaTransporteRepository;

    @Override
    public List<ZonaTransporte> listadoZonaTransporte() {
        return zonaTransporteRepository.findAll();
    }
}
