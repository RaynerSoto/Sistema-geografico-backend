package cu.edu.cujae.core.dto;

import cu.edu.cujae.core.enums.Estado;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

public class Registro {
    @NonNull
    private String uuid;
    @NonNull
    private Usuario usuario;
    @NonNull
    @Size(min = 1,message = "La actividad no puede estar vacía")
    private String actividad;
    @NonNull
    @Size(min = 7,message = "La dirección IP es demasiado corta")
    private String ip;
    @NonNull
    @Size(min = 1,message = "El nombre del PC no puede estar vacío")
    private String nombrePC;
    @NonNull
    private Estado estado;
}
