package cu.edu.cujae.logs.core.services.servicesimpl;

import cu.edu.cujae.logs.core.exception.SearchException;
import cu.edu.cujae.logs.core.mapper.Sexo;
import cu.edu.cujae.logs.core.mapper.Usuario;
import cu.edu.cujae.logs.core.repository.UsuarioRepository;
import cu.edu.cujae.logs.core.services.UsuarioServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UsuarioServiceInterfaces {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuario> listarUsuarios() throws Exception{
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> listarUsuariosNoSuperAdministrador() throws Exception{
        return usuarioRepository.listadoUsuarioActivosNoSuperAdministrador();
    }

    @Override
    public Usuario obtenerUsuarioPorUsernameAndPassword(String username,String password){
        for (Usuario user : usuarioRepository.listadoUsuarioActivos()){
            if(user.getUsername().equalsIgnoreCase(username) && passwordEncoder.matches(password,user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public Optional<Usuario> obtenerUsuarioEmailUsernameName(String email, String username, String name, Sexo sexo) throws SearchException {
        return Optional.ofNullable(usuarioRepository.findByEmailEqualsAndUsernameEqualsIgnoreCaseAndNameEqualsAndSexoEquals(email,username,name,sexo)).orElseThrow(
                ()->new SearchException("Usuario no encontrado")
        );
    }

    @Override
    public Optional<Usuario> obtenerUsuarioUsernameInactivo(String username) throws Exception {
        return Optional.ofNullable(usuarioRepository.findUsuarioByUsernameEqualsIgnoreCaseAndActivoIsFalse(username).orElseThrow(
                () -> new SearchException("Usuario no encontrado")));
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
            usuario.setFechaEliminacion(Timestamp.valueOf(LocalDateTime.now()));
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
    public Usuario buscarUsuarioReturnedNull(Long id){
        try {
            return buscarUsuario(id);
        }
        catch (Exception e) {
            return null;
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
        if (usuarioActivoUsername(username).isPresent() || usuarioInactivoUsername(username).isPresent()) {
            throw new Exception("El nombre de usuario está en uso");
        }
        else if (usuarioActivoEmail(email).isPresent()) {
            throw new Exception("El email está en uso");
        }
    }

    @Override
    public Optional<Usuario> usuarioInactivoUsername(String username){
        return usuarioRepository.findUsuarioByUsernameEqualsIgnoreCaseAndActivoIsFalse(username);
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
