package cu.edu.cujae.gestion.core.repository;

import cu.edu.cujae.gestion.core.mapping.Entidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadRepository extends JpaRepository<Entidad, Long> {
    public boolean existsByNombreEqualsIgnoreCase(String nombre);

    public boolean existsByNombreEqualsIgnoreCaseAndAndUuidNot(String nombre, Long uuid);
}
