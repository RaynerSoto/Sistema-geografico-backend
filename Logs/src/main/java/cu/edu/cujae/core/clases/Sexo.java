package cu.edu.cujae.core.clases;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sexo {
    @NotNull(message = "El identificador no puede ser null")
    @NotBlank(message = "El identificador no puede estar vacío")
    private String uuid;

    @NotNull(message = "El nombre del sexo no puede ser null")
    @NotBlank(message = "El nombre del sexo no puede estar vacío")
    @Size(min = 4,max = 100,message = "El nombre del sexo debe estar entre 4 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La sigla no puede ser null")
    @NotBlank(message = "La sigla no puede estar vacío")
    @Size(min = 1,max = 1,message = "La sigla solamente tiene un caracter")
    private String sigla;

}
