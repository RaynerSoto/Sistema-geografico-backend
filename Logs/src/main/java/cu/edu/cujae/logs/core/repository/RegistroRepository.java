package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapping.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
}
