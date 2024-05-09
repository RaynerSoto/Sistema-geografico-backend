package cu.edu.cujae.core.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Rol {
    @NonNull
    private String uuid;
    @NonNull
    @Size(min = 4,max = 50,message = "El rol debe tener entre 4 y 50 caracteres")
    private String rol;
}
