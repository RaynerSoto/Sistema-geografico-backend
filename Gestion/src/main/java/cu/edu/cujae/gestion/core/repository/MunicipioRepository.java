package cu.edu.cujae.gestion.core.repository;

import cu.edu.cujae.gestion.core.mapper.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MunicipioRepository extends JpaRepository<Municipio,Long> {
    Optional<Municipio> findByNombreEqualsIgnoreCase(String nombre);
}
