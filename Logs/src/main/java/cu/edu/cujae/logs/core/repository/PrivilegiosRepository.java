package cu.edu.cujae.logs.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrivilegiosRepository extends JpaRepository<Privilegio,Long> {
    Optional<Privilegio> findByCodigoEqualsIgnoreCase(String nombre);
}
