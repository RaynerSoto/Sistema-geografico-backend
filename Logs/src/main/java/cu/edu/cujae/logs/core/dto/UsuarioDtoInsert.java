package cu.edu.cujae.logs.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.logs.core.mapping.Usuario;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoInsert extends UsuarioDto{
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean reactivado;

    public UsuarioDtoInsert(@Min(value = 1, message = "El valor mínimo del id es 1") Long uuid, @NotNull(message = "El nombre de usuario no puede ser nulo") @NotBlank(message = "El nombre de usuario no puede ser vacío") String username, @NotNull(message = "El nombre completo no puede ser nulo") @NotBlank(message = "El nombre completo no puede ser vacío") @Size(min = 2, max = 100, message = "El nombre completo debe tener entre 2 y 100 caracteres") String name, @NotBlank(message = "La contraseña no puede estar llena de espacios") @Size(min = 1, max = 1000, message = "La contraseña no se encuentra en un rango de 1 y 1000 caracteres") @NotNull(message = "La contraseña no puede ser nulo") String password, @Email(message = "Correo no válido") @NotBlank(message = "El correo no puede ser vacío") @NotNull(message = "El correo no puede ser nulo") String email, @NotBlank(message = "El rol no puede ser vacío") @NotNull(message = "El rol no puede ser nulo") String rol, @NotNull(message = "El sexo no puede ser nulo") @NotBlank(message = "El sexo no puede estar vacío") String sexo, @NotNull(message = "Este dato no puede ser null") boolean activo, String fechaCreacion, String fechaEliminacion, boolean reactivado) {
        super(uuid, username, name, password, email, rol, sexo, activo, fechaCreacion, fechaEliminacion);
        this.reactivado = reactivado;
    }

    public UsuarioDtoInsert(Usuario usuario, boolean reactivado) {
        super(usuario);
        this.reactivado = reactivado;
    }

    public UsuarioDtoInsert(Optional<Usuario> usuario, boolean reactivado) {
        super(usuario);
        this.reactivado = reactivado;
    }
}
