package cu.edu.cujae.logs.core.dto.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.logs.core.mapping.Usuario;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginDto{
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Min(value = 1,message = "El valor mínimo del id es 1")
    private Long uuid;

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @NotBlank(message = "El nombre de usuario no puede ser vacío")
    private String username;

    @Size(min = 4,max = 1000,message = "La contraseña no se encuentra en un rango de 4 y 1000 caracteres")
    @NotNull(message = "La contraseña no puede ser nulo")
    private String password;

    public UsuarioLoginDto(Usuario user){
        this.uuid = user.getUuid();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
