package cu.edu.cujae.gestion.core.repository;

import cu.edu.cujae.gestion.core.mapper.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntidadRepository extends JpaRepository<Entidad, Long> {
    boolean existsByNombreEqualsIgnoreCase(String nombre);

    boolean existsByNombreEqualsIgnoreCaseAndAndUuidNot(String nombre, Long uuid);

    Optional<Entidad> findByNombreEqualsIgnoreCase(String nombre);
}
