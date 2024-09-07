package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapper.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findByNombreEqualsIgnoreCase(String nombre);
}
