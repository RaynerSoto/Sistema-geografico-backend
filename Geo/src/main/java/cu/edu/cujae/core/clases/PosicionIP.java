package cu.edu.cujae.core.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PosicionIP {

    @NotNull(message = "El ip no puede ser nulo")
    @NotBlank(message = "El ip no puede ser vacío")
    private String ip;

    @NotNull(message = "El país no puede ser nulo")
    @NotBlank(message = "El país no puede ser vacío")
    private String pais;
}
