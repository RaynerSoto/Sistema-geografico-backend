package cu.edu.cujae.logs.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.logs.core.mapping.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SexoDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "El nombre del sexo no puede ser null")
    @NotBlank(message = "El nombre del sexo no puede estar vacío")
    @Size(min = 4,max = 100,message = "El nombre del sexo debe estar entre 4 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La sigla no puede ser null")
    @NotBlank(message = "La sigla no puede estar vacío")
    @Size(min = 1,max = 5,message = "Las sigla solamente tienen entre 1 y 5 caracteres")
    private String sigla;

    public SexoDto(Sexo sexo){
        this.id = sexo.getId();
        this.nombre = sexo.getNombre();
        this.sigla = sexo.getSigla();
    }
}
