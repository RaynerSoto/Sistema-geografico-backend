package cu.edu.cujae.gestion.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Min(value = 1,message = "El valor mínimo del id es 1")
    private Long uuid;

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @NotBlank(message = "El nombre de usuario no puede ser vacío")
    private String username;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @NotBlank(message = "El nombre completo no puede ser vacío")
    @Size(min = 2,max = 100,message = "El nombre completo debe tener entre 2 y 100 caracteres")
    private String name;

    @NotBlank(message = "La contraseña no puede estar llena de espacios")
    @Size(min = 1,max = 1000,message = "La contraseña no se encuentra en un rango de 1 y 1000 caracteres")
    @NotNull(message = "La contraseña no puede ser nulo")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Email(message = "Correo no válido")
    @NotBlank(message = "El correo no puede ser vacío")
    @NotNull(message = "El correo no puede ser nulo")
    private String email;

    @NotBlank(message = "El rol no puede ser vacío")
    @NotNull(message = "El rol no puede ser nulo")
    private String rol;

    @NotNull(message = "El sexo no puede ser nulo")
    @NotBlank(message = "El sexo no puede estar vacío")
    private String sexo;

    @NotNull(message = "Este dato no puede ser null")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean activo;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String fechaCreacion;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String fechaEliminacion;
}
