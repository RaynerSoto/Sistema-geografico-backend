package cu.edu.cujae.logs.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegioRolDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "El rol no puede ser null")
    @NotBlank(message = "El rol no puede estar vacío")
    @Size(min = 4,max = 50,message = "El rol debe tener entre 4 y 50 caracteres")
    private String rol;

    @NotNull(message = "El código no puede ser null")
    @Size(min = 1,message = "El código debe tener mínimo 1 caracter")
    @NotBlank(message = "El código no puede estar vacío")
    private String codigo;

}
