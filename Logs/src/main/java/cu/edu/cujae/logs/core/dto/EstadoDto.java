package cu.edu.cujae.logs.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.logs.core.mapping.Estado;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long uuid;

    @Size(min = 4,max = 100,message = "El nombre del estado debe estar entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del estado no puede estar vacío")
    @NotNull(message = "El nombre del estado no puede ser null")
    private String nombre;

    @Size(min = 4,max = 100,message = "La descrpción del Estado debe estar entre 4 y 100 caracteres")
    @NotBlank(message = "La descrpción del Estado no puede estar vacío")
    @NotNull(message = "La descrpción del Estado no puede ser null")
    private String descripcion;

    public EstadoDto(Estado estado) {
        this.uuid = estado.getUuid();
        this.nombre = estado.getNombre();
        this.descripcion = estado.getDescripcion();
    }

    public EstadoDto(Optional<Estado>estado){
        this.uuid = estado.get().getUuid();
        this.nombre = estado.get().getNombre();
        this.descripcion = estado.get().getDescripcion();
    }
}
