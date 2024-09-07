package cu.edu.cujae.logs.core.repository;

import cu.edu.cujae.logs.core.mapper.Sexo;
import cu.edu.cujae.logs.core.mapper.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    public Usuario findByUsernameEqualsIgnoreCaseAndPasswordEqualsAndActivoIsTrue(String username,String password);

    public Optional<Usuario> findByUsernameEqualsIgnoreCaseAndActivoIsTrue(String username);

    public Optional<Usuario> findByEmailEqualsIgnoreCaseAndActivoIsTrue(String email);

    public Optional<Usuario> findByEmailEqualsIgnoreCaseAndActivoIsTrueAndUuidNot(String email,Long uuid);

    public Optional<Usuario> findByUsernameEqualsIgnoreCaseAndActivoIsTrueAndUuidNot(String username,Long id);

    public Optional<Usuario> findFirstByEmailEqualsIgnoreCaseAndActivoIsTrueAndUsernameEquals(String email,String username);

    @Query("from Usuario as user INNER JOIN Rol as rol on rol.uuid = user.rol.uuid and rol.rol like 'Super Administrador' where user.activo = true")
    public List<Usuario> listadoUsuarioActivosNoSuperAdministrador();

    @Query("from Usuario as user where user.activo = true")
    public List<Usuario> listadoUsuarioActivos();

    @Query("from Usuario as user where user.username like :username and user.password like :password")
    public  Optional<Usuario> findByUsernameAndPassword(String username,String password);

    UserDetails findByUsernameEqualsIgnoreCase(String username);

    Optional<Usuario> findByUuidEquals(Long id);

    Optional<Usuario> findByEmailEqualsAndUsernameEqualsIgnoreCaseAndNameEqualsAndSexoEquals(String email, String username, String name, Sexo sexo);
}
