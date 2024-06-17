package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapping.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    public Optional<Rol> findByRolEqualsIgnoreCase(String rol) throws Exception;

}
