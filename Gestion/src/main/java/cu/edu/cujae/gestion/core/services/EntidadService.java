package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.mapping.Entidad;
import cu.edu.cujae.gestion.core.repository.EntidadRepository;
import cu.edu.cujae.gestion.core.servicesInterfaces.EntidadServicesInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntidadService implements EntidadServicesInterfaces {

    @Autowired
    private EntidadRepository entidadRepository;

    public void insertarEntidad(Entidad entidad) {
        entidadRepository.save(entidad);
    }

    public void modificarEntidad(Entidad entidad) {
        if (entidadRepository.existsById(entidad.getUuid()))
            entidadRepository.save(entidad);
    }

    public void eliminarEntidad(Long id) {
        if (entidadRepository.existsById(id))
            entidadRepository.deleteById(id);
    }

    @Override
    public List<Entidad> listarEntidad() {
        return entidadRepository.findAll();
    }
}
