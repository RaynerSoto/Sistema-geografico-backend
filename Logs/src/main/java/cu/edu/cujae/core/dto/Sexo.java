package cu.edu.cujae.core.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Sexo {
    @NotBlank
    private String uuid;

    @NotBlank
    @Size(min = 4,max = 50,message = "El sexo debe estar entre 4 y 50 caracteres")
    private String nombre;

    @NotBlank
    @Size(min = 1,max = 1,message = "La sigla solamente tiene un caracter")
    private String sigla;

}
