package cu.edu.cujae.gestion.core.mappingSecurity;
import cu.edu.cujae.gestion.core.dto.UsuarioDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails{
    @Id
    @Column(name = "usuarioID",nullable = false, length = 36, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQUENCE")
    private Long uuid;

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @Size(min = 4,max = 50,message = "El nombre de usuario se encuentra en un rango de 4 y 50 caracteres")
    @NotBlank(message = "El nombre de usuario no puede ser vacío")
    @Column(name = "username",nullable = false, length = 100,unique = true,updatable = false)
    private String username;

    @Size(min = 4,max = 1000,message = "La contraseña no se encuentra en un rango de 4 y 1000 caracteres")
    @NotNull(message = "La contraseña no puede ser nulo")
    @Column(name = "password",nullable = false, length = 1000)
    private String password;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @Size(min = 2,max = 100,message = "El nombre completo debe tener entre 2 y 100 caracteres")
    @NotBlank(message = "El nombre completo no puede ser vacío")
    @Column(name = "nombre",nullable = false, length = 100,unique = true)
    private String name;

    @NotNull(message = "El correo no puede ser nulo")
    @NotBlank(message = "El correo no puede ser vacío")
    @Size(max = 100,message = "El correo tiene un máximo de 100 caracteres")
    @Email(message = "Correo no válido")
    @Column(name = "email",nullable = false, length = 100,unique = true)
    private String email;

    @NotNull(message = "El rol no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rolID")
    private String rol;

    @NotNull(message = "El sexo no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sexoID")
    private String sexo;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @NotNull(message = "La fecha de creación no puede ser null")
    @Column(name = "creacion",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaCreacion;

    @Column(name = "eliminacion",nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fechaEliminacion;

    public Usuario(Usuario usuario){
        this.uuid = usuario.getUuid();
        this.username = usuario.getUsername();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        this.rol = usuario.getRol();
        this.sexo = usuario.getSexo();
        this.activo = usuario.isActivo();
        this.password = usuario.getPassword();
        this.fechaCreacion = usuario.getFechaCreacion();
        this.fechaEliminacion = usuario.getFechaEliminacion();
    }

    public Usuario(UsuarioDto usuario){
        this.uuid = usuario.getUuid();
        this.username = usuario.getUsername();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        this.rol = usuario.getRol();
        this.sexo = usuario.getSexo();
        this.activo = usuario.isActivo();
        this.password = usuario.getPassword();
    }

    public Usuario(Optional<Usuario> usuario){
        this.uuid = usuario.get().getUuid();
        this.username = usuario.get().getUsername();
        this.name = usuario.get().getName();
        this.email = usuario.get().getEmail();
        this.rol = usuario.get().getRol();
        this.sexo = usuario.get().getSexo();
        this.activo = usuario.get().isActivo();
        this.password = usuario.get().getPassword();
        this.fechaCreacion = Timestamp.valueOf(LocalDateTime.now());
        this.fechaEliminacion = null;
    }

    public Usuario(Usuario usuario, String password){
        this.uuid = usuario.getUuid();
        this.username = usuario.getUsername();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        this.rol = usuario.getRol();
        this.sexo = usuario.getSexo();
        this.activo = usuario.isActivo();
        this.password = password;
        this.fechaCreacion = Timestamp.valueOf(LocalDateTime.now());
        this.fechaEliminacion = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_"+this.rol));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.activo;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.activo;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.activo;
    }

    @Override
    public boolean isEnabled() {
        return this.activo;
    }
}
