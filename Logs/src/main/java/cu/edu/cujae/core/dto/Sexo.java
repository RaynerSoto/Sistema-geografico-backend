package cu.edu.cujae.core.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sexo {
    private String uuid;
    @Size(min = 4,max = 50,message = "El sexo debe estar entre 4 y 50 caracteres")
    private String nombre;
    @Size(min = 1,max = 1,message = "Esto es solamente la sigla del sexo en cuestión")
    private char sigla;

}
