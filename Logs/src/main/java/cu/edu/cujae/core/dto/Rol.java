package cu.edu.cujae.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Rol {

    @NotBlank
    private String uuid;

    @NotBlank
    @Size(min = 4,max = 50,message = "El rol debe tener entre 4 y 50 caracteres")
    private String rol;
}
