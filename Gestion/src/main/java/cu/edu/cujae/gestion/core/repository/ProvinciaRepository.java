package cu.edu.cujae.gestion.core.repository;

import cu.edu.cujae.gestion.core.mapping.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    public Optional<Provincia> findByNombreEqualsIgnoreCase(String nombre) throws Exception;
}
