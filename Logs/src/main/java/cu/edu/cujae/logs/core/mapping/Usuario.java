package cu.edu.cujae.logs.core.mapping;

import cu.edu.cujae.logs.core.dto.UsuarioDto;
import cu.edu.cujae.logs.core.exception.GoodException;
import cu.edu.cujae.logs.core.utils.Validacion;
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

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
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
    private Rol rol;

    @NotNull(message = "El sexo no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sexoID")
    private Sexo sexo;

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

    public Usuario(UsuarioDto usuarioDto,Rol rol, Sexo sexo) throws Exception {
        this.uuid = usuarioDto.getUuid();
        this.username = usuarioDto.getUsername();
        this.name = usuarioDto.getName();
        this.email = usuarioDto.getEmail();
        this.activo = usuarioDto.isActivo();
        this.rol = rol;
        this.sexo = sexo;
        this.password = usuarioDto.getPassword();
        this.fechaCreacion = Timestamp.valueOf(LocalDateTime.now());
        this.fechaEliminacion = null;
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

    @PrePersist
    public void prePersist(){
        try {
            Validacion.validarUnsupportedOperationException(this);
        }catch (Exception e){
            throw new UnsupportedOperationException(e.getMessage());
        }
        if (this.isActivo() == false){
            throw new UnsupportedOperationException("El usuario no puede ser aceptado por no encontrarse activo");
        }
    }

    @PreUpdate
    public void preUpdate(){
        if (this.rol.getRol().equalsIgnoreCase("SuperAdministrador"))
            throw new UnsupportedOperationException("No se puede modificar el usuario Super Administrador");
        String validacion = Validacion.comprobacionValidador(this);
        if (validacion.isBlank() == false){
            throw new UnsupportedOperationException(validacion);
        } else if (this.getUuid() == null) {
            throw new UnsupportedOperationException("El id del usuario no puede ser nulo");
        }
    }

    @PreRemove
    public void preRemove(){
        this.setActivo(false);
        throw new GoodException("Usuario eliminado");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority("ROLE_"+this.rol.getRol()));
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
