package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapping.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findByUsernameEqualsIgnoreCaseAndActivoIsTrue(String username);

    public Optional<Usuario> findByEmailEqualsIgnoreCaseAndActivoIsTrue(String email);

    public Optional<Usuario> findByEmailEqualsIgnoreCaseAndActivoIsTrueAndUuidNot(String email,Long uuid);

    public Optional<Usuario> findByUsernameEqualsIgnoreCaseAndActivoIsTrueAndUuidNot(String username,Long id);

    public Optional<Usuario> findFirstByEmailEqualsIgnoreCaseAndActivoIsTrueAndUsernameEquals(String email,String username);
}
