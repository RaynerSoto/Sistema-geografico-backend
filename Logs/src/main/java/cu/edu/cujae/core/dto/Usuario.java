package cu.edu.cujae.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Usuario {
    @NonNull
    private String uuid;
    @NonNull
    private String username;
    @NonNull
    private String name;
    @NonNull
    private String lastName; //Apellido
    @NonNull
    private String email;
    @NonNull
    private Rol rol;
    @NonNull
    private Sexo sexo;
}
