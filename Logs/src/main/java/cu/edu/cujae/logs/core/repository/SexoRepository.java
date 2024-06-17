package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapping.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SexoRepository extends JpaRepository<Sexo, Long> {
    public Optional<Sexo> findByNombreEqualsIgnoreCase(String nombre);
}
