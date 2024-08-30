package cu.edu.cujae.gestion.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class General {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long uuid;

    @NotBlank(message = "El municipio no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El municipio no puede ser nulo")
    private String municipio;

    @NotBlank(message = "La provincia no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "La provincia no puede ser nulo")
    private String provincia;

    @NotBlank(message = "La direccion de la entidad no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "La direccion de la entidad no puede ser null")
    private String direccion;

}
