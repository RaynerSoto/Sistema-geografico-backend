package cu.edu.cujae.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Usuario {
    @NotBlank
    private String uuid;

    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName; //Apellido

    @NotBlank
    @Email(message = "Correo no válido")
    private String email;
    @NotBlank
    private Rol rol;
    @NotBlank
    private Sexo sexo;
}
