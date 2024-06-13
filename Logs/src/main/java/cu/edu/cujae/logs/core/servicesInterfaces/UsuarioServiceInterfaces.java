package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.clases.Usuario;

import java.util.List;

public interface UsuarioServiceInterfaces {
    public List<Usuario> listarUsuarios() throws Exception;

    public void insertarUsuario(Usuario usuario) throws Exception;

    public void modificarUsuario(Usuario usuario, Long id) throws Exception;

    public void eliminarUsuario(Long id) throws Exception;

    public Usuario buscarUsuario(Long id) throws Exception;
}
