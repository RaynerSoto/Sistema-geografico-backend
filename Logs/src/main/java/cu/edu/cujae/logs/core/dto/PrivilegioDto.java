package cu.edu.cujae.logs.core.dto;

import cu.edu.cujae.logs.core.mapping.Privilegio;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegioDto {
    @NotNull(message = "El código no puede ser null")
    @Size(min = 1,message = "El código debe tener mínimo 1 caracter")
    @NotBlank(message = "El código no puede estar vacío")
    private String codigo;

    @NotNull(message = "La descripción no puede ser null")
    @NotBlank(message = "La descripción no puede estar vacío")
    @Size(min = 1,message = "La descripción debe tener mínimo 1 caracter")
    private String descripcion;

    public PrivilegioDto(Privilegio privilegio) {
        this.codigo = privilegio.getCodigo();
        this.descripcion = privilegio.getDescripcion();
    }

}
