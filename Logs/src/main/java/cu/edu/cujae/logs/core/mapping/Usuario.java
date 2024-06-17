package cu.edu.cujae.logs.core.mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cu.edu.cujae.logs.core.dto.UsuarioDto;
import cu.edu.cujae.logs.core.services.RolService;
import cu.edu.cujae.logs.core.services.SexoService;
import cu.edu.cujae.logs.core.servicesInterfaces.SexoServiceInterfaces;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "usuarioID",nullable = false, length = 36, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQUENCE", allocationSize = 1)
    private Long uuid;

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @NotBlank(message = "El nombre de usuario no puede ser vacío")
    @Size(min = 4,max = 50,message = "El nombre de usuario se encuentra en un rango de 4 y 50 caracteres")
    @Column(name = "username",nullable = false, length = 50)
    private String username;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @NotBlank(message = "El nombre completo no puede ser vacío")
    @Size(min = 2,max = 100,message = "El nombre completo debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre",nullable = false, length = 100)
    private String name;

    @NotNull(message = "El correo no puede ser nulo")
    @NotBlank(message = "El correo no puede ser vacío")
    @Email(message = "Correo no válido")
    @Column(name = "email",nullable = false, length = 50)
    private String email;

    @NotNull(message = "El rol no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolID")
    @JsonIgnore
    private Rol rol;


    @OneToMany(mappedBy = "usuario")
    private List<Registro> registroList;

    @NotNull(message = "El sexo no puede ser nulo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sexoID")
    private Sexo sexo;

    @Column(name = "activo", nullable = false)
    private boolean activo;



    public Usuario(Usuario usuario){
        this.uuid = usuario.getUuid();
        this.username = usuario.getUsername();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        this.rol = usuario.getRol();
        this.sexo = usuario.getSexo();
        this.activo = usuario.isActivo();
    }

    public Usuario(UsuarioDto usuarioDto,Rol rol, Sexo sexo) throws Exception {
        this.uuid = usuarioDto.getUuid();
        this.username = usuarioDto.getUsername();
        this.name = usuarioDto.getName();
        this.email = usuarioDto.getEmail();
        this.activo = usuarioDto.isActivo();
        this.rol = rol;
        this.sexo = sexo;
    }
}
