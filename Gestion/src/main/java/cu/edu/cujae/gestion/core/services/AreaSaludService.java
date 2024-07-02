package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.mapping.AreaSalud;
import cu.edu.cujae.gestion.core.repository.AreaSaludRepository;
import cu.edu.cujae.gestion.core.servicesInterfaces.AreaSaludServicesInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaSaludService implements AreaSaludServicesInterfaces {
    @Autowired
    private AreaSaludRepository areaSaludRepository;

    @Override
    public List<AreaSalud> getAreaSalud() {
        return areaSaludRepository.findAll();
    }
}
