package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.mapping.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceInterfaces {
    public List<Usuario> listarUsuarios() throws Exception;

    public Page<Usuario> listarUsuarios(Pageable pageable) throws Exception;

    public void insertarUsuario(Usuario usuario) throws Exception;

    public void modificarUsuario(Usuario usuario) throws Exception;

    public void eliminarUsuario(Long id) throws Exception;

    public Usuario buscarUsuario(Long id) throws Exception;

    public Optional<Usuario> usuarioActivo(String email,String username) throws Exception;

    public Optional<Usuario> usuarioActivoEmail(String email) throws Exception;

    public Optional<Usuario> usuarioActivoUsername(String username) throws Exception;

    public void validarUsuarioInsertar(String email, String username) throws Exception;

    public void validarUsuarioModificar(String email, String username,Long id) throws Exception;

    public Optional<Usuario> buscarUsuarioPorUsernameActivo(String username) throws Exception;

    public List<Usuario> listarUsuariosNoSuperAdministrador() throws Exception;
    }
