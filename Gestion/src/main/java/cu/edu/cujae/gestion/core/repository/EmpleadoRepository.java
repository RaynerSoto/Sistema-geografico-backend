package cu.edu.cujae.gestion.core.repository;

import cu.edu.cujae.gestion.core.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long>{
    Optional<Empleado> findByCiEqualsIgnoreCase(String ci);

    boolean existsByCiEqualsIgnoreCase(String ci);
}
