package cu.edu.cujae.logs.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.logs.core.mapping.Registro;
import cu.edu.cujae.logs.core.mapping.Rol;
import cu.edu.cujae.logs.core.mapping.Sexo;
import cu.edu.cujae.logs.core.mapping.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long uuid;

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @NotBlank(message = "El nombre de usuario no puede ser vacío")
    private String username;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @NotBlank(message = "El nombre completo no puede ser vacío")
    @Size(min = 2,max = 100,message = "El nombre completo debe tener entre 2 y 100 caracteres")
    private String name;

    @NotNull(message = "El correo no puede ser nulo")
    @NotBlank(message = "El correo no puede ser vacío")
    @Email(message = "Correo no válido")
    private String email;

    @NotNull(message = "El rol no puede ser nulo")
    @NotBlank(message = "El rol no puede ser vacío")
    private String rol;


    @NotNull(message = "El sexo no puede ser nulo")
    @NotBlank(message = "El sexo no puede estar vacío")
    private String sexo;


    @NotNull(message = "Este dato no puede ser null")
    private boolean activo;

    public UsuarioDto(Usuario usuario) {
        this.uuid = usuario.getUuid();
        this.username = usuario.getUsername();
        this.name = usuario.getName();
        this.email = usuario.getEmail();
        this.rol = usuario.getRol().getRol();
        this.sexo = usuario.getSexo().getNombre();
        this.activo = usuario.isActivo();
    }

    public UsuarioDto(Optional<Usuario> usuario) {
        this.uuid = usuario.get().getUuid();
        this.username = usuario.get().getUsername();
        this.name = usuario.get().getName();
        this.email = usuario.get().getEmail();
        this.rol = usuario.get().getRol().getRol();
        this.sexo = usuario.get().getSexo().getNombre();
        this.activo = usuario.get().isActivo();
    }
}
