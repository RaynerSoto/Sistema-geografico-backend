package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapping.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
