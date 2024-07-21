package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.exception.SearchException;
import cu.edu.cujae.logs.core.mapping.Sexo;
import cu.edu.cujae.logs.core.mapping.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceInterfaces {
    List<Usuario> listarUsuarios() throws Exception;

    Page<Usuario> listarUsuarios(Pageable pageable) throws Exception;

    void insertarUsuario(Usuario usuario) throws Exception;

    void modificarUsuario(Usuario usuario) throws Exception;

    void eliminarUsuario(Long id) throws Exception;

    Usuario buscarUsuario(Long id) throws Exception;

    Optional<Usuario> usuarioActivo(String email,String username) throws Exception;

    Optional<Usuario> usuarioActivoEmail(String email) throws Exception;

    Optional<Usuario> usuarioActivoUsername(String username) throws Exception;

    void validarUsuarioInsertar(String email, String username) throws Exception;

    void validarUsuarioModificar(String email, String username,Long id) throws Exception;

    Optional<Usuario> buscarUsuarioPorUsernameActivo(String username) throws Exception;

    List<Usuario> listarUsuariosNoSuperAdministrador() throws Exception;

    Usuario obtenerUsuarioPorUsernameAndPassword(String username,String password);

    Optional<Usuario> obtenerUsuarioEmailUsernameName(String email, String username, String name, Sexo sexo) throws SearchException;
}
