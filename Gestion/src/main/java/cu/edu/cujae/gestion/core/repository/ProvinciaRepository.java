package cu.edu.cujae.gestion.core.repository;

import cu.edu.cujae.gestion.core.model.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinciaRepository extends JpaRepository<Provincia, Integer> {
    Optional<Provincia> findByNombreEqualsIgnoreCase(String nombre) throws Exception;
}
