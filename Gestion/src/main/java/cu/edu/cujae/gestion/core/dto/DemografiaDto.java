package cu.edu.cujae.gestion.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemografiaDto {
    private String origen;
    private String destino;
    private int cantidad = 0;
}
