package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.mapping.Provincia;
import cu.edu.cujae.gestion.core.repository.ProvinciaRepository;
import cu.edu.cujae.gestion.core.servicesInterfaces.ProvinciaServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService implements ProvinciaServiceInterfaces {
    @Autowired
    private ProvinciaRepository provinciaRepository;

    @Override
    public List<Provincia> listadoProvincia() {
        return provinciaRepository.findAll();
    }
}
