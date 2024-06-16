package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.repository.UsuarioRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.UsuarioServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UsuarioServiceInterfaces {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios() throws Exception{
        return usuarioRepository.findAll();
    }

    @Override
    public void insertarUsuario(Usuario usuario) throws Exception{
        usuarioRepository.save(usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario, Long id) throws Exception {
        if (usuarioRepository.existsById(id)) {
            usuario.setUuid(id);
            usuarioRepository.save(usuario);
        }
        else {
            throw new Exception("No existe el usuario a modificar");
        }
    }

    @Override
    public void eliminarUsuario(Long id) throws Exception{
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        }
        else {
            throw new Exception("No existe el usuario");
        }
    }

    @Override
    public Usuario buscarUsuario(Long id) throws Exception{
        if (usuarioRepository.existsById(id)) {
            return usuarioRepository.findById(id).get();
        }
        else{
            throw new Exception("No existe el usuario");
        }
    }
}
