package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.exception.SearchException;
import cu.edu.cujae.logs.core.mapping.Usuario;
import cu.edu.cujae.logs.core.repository.UsuarioRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.UsuarioServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UsuarioServiceInterfaces {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios() throws Exception{
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> listarUsuariosNoSuperAdministrador() throws Exception{
        return usuarioRepository.listadoUsuarioActivosNoSuperAdministrador();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorUsernameAndPassword(String username, String password) throws Exception {
        return Optional.ofNullable(usuarioRepository.findByUsernameAndPassword(username,password).orElseThrow(
                () -> new SearchException("Usuario no encontrado")
        ));
    }

    @Override
    public Page<Usuario> listarUsuarios(Pageable pageable) throws Exception{
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public void insertarUsuario(Usuario usuario) throws Exception{
        usuarioRepository.save(usuario);
    }

    @Override
    public void modificarUsuario(Usuario usuario) throws Exception {
        if (usuarioRepository.existsById(usuario.getUuid())) {
            usuarioRepository.save(usuario);
        }
        else {
            throw new SearchException("No existe el usuario a modificar");
        }
    }

    @Override
    public void eliminarUsuario(Long id) throws Exception{
        if (usuarioRepository.existsById(id)) {
            Usuario usuario = usuarioRepository.findById(id).get();
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
        }
        else {
            throw new SearchException("No existe el usuario");
        }
    }

    @Override
    public Usuario buscarUsuario(Long id) throws Exception{
        if (usuarioRepository.existsById(id)) {
            return usuarioRepository.findById(id).get();
        }
        else{
            throw new SearchException("No existe el usuario");
        }
    }

    @Override
    public Optional<Usuario> usuarioActivo(String email, String username) throws Exception {
        return usuarioRepository.findFirstByEmailEqualsIgnoreCaseAndActivoIsTrueAndUsernameEquals(email,username);
    }

    @Override
    public Optional<Usuario> usuarioActivoEmail(String email) throws Exception {
        return usuarioRepository.findByEmailEqualsIgnoreCaseAndActivoIsTrue(email);
    }

    @Override
    public Optional<Usuario> usuarioActivoUsername(String username) throws Exception {
        return usuarioRepository.findByUsernameEqualsIgnoreCaseAndActivoIsTrue(username);
    }

    @Override
    public void validarUsuarioInsertar(String email, String username) throws Exception {
        if (usuarioActivoUsername(username).isPresent()) {
            throw new Exception("El nombre de usuario está en uso");
        }
        else if (usuarioActivoEmail(email).isPresent()) {
            throw new Exception("El email está en uso");
        }
    }

    public void validarUsuarioModificar(String email, String username,Long id) throws Exception{
        if (usuarioRepository.findByEmailEqualsIgnoreCaseAndActivoIsTrueAndUuidNot(email,id).isPresent()){
            throw new Exception("El nombre de usuario está en uso");
        }
        else if (usuarioRepository.findByUsernameEqualsIgnoreCaseAndActivoIsTrueAndUuidNot(username,id).isPresent()){
            throw new Exception("El email está en uso");
        }
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorUsernameActivo(String username) throws Exception {
        return Optional.ofNullable(usuarioRepository.findByUsernameEqualsIgnoreCaseAndActivoIsTrue(username).orElseThrow(
                () -> new SearchException("No existe el usuario")));
    }
}
