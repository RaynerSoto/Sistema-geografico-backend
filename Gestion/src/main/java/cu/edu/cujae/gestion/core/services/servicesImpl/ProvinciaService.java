package cu.edu.cujae.gestion.core.services.servicesImpl;

import cu.edu.cujae.gestion.core.exception.SearchException;
import cu.edu.cujae.gestion.core.mapper.Provincia;
import cu.edu.cujae.gestion.core.repository.ProvinciaRepository;
import cu.edu.cujae.gestion.core.services.ProvinciaServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinciaService implements ProvinciaServiceInterfaces {

    private final ProvinciaRepository provinciaRepository;

    @Autowired
    public ProvinciaService(ProvinciaRepository provinciaRepository) {
        this.provinciaRepository = provinciaRepository;
    }

    @Override
    public List<Provincia> listadoProvincia() {
        return provinciaRepository.findAll();
    }

    @Override
    public Optional<Provincia> buscarProvinciaPorNombre(String nombre) throws Exception{
        return Optional.ofNullable(provinciaRepository.findByNombreEqualsIgnoreCase(nombre).orElseThrow(
                () -> new SearchException("Provincia no encontrado")
        ));
    }
}
