package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapping.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> existsUsuarioByUsernameEqualsIgnoreCaseAndActivoIsTrue(String username);

    public Optional<Usuario> findByUsernameEqualsIgnoreCaseAndActivoIsTrue(String username);

    public Optional<Usuario> findByEmailEqualsIgnoreCaseAndActivoIsTrue(String email);

    public Optional<Usuario> findByEmailEqualsIgnoreCaseAndActivoIsTrueAndUuidNot(String email,Long uuid);

    public Optional<Usuario> findByUsernameEqualsIgnoreCaseAndActivoIsTrueAndUuidNot(String username,Long id);

    public Optional<Usuario> findFirstByEmailEqualsIgnoreCaseAndActivoIsTrueAndUsernameEquals(String email,String username);

    @Query("from Usuario as user INNER JOIN Rol as rol on rol.uuid = user.rol.uuid and rol.rol like 'Super Administrador' where user.activo = true")
    public List<Usuario> listadoUsuarioActivosNoSuperAdministrador();

    @Query("from Usuario as user where user.username like ? and user.password like ?")
    public  Optional<Usuario> findByUsernameAndPassword(String username,String password);
}
