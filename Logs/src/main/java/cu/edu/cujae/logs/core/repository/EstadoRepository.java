package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapping.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    public Optional<Estado> findByNombreEqualsIgnoreCase(String nombre);
}
