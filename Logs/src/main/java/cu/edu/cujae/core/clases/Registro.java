package cu.edu.cujae.core.clases;

import cu.edu.cujae.core.enums.Estado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Registro {
    @NotNull(message = "El identificador no puede ser null")
    @NotBlank(message = "El identificador no puede estar vacío")
    private String uuid;

    @NotNull(message = "El usuario no puede ser null")
    @NotBlank(message = "El usuario no puede estar vacío")
    private Usuario usuario;

    @NotNull(message = "La actividad no puede ser null")
    @NotBlank(message = "La actividad no puede estar vacío")
    @Size(min = 6,max = 50,message = "La actividad dedbe estar entre 6 y 50 caracteres")
    private String actividad;

    @NotNull(message = "El IP no puede ser null")
    @NotBlank(message = "El IP no puede estar vacío")
    @Size(min = 7,max = 15,message = "La dirección IP con formato XXX.XXX.XXX es incorrecta")
    private String ip;

    @NotNull(message = "El nombre del PC no puede ser null")
    @NotBlank(message = "El identificador no puede estar vacío")
    @Size(min = 1,message = "El nombre del PC debe tener mínimo 1 caracter")
    private String nombrePC;


    @NotNull(message = "El estado no puede ser null")
    @NotBlank(message = "El estado no puede estar vacío")
    private Estado estado;
}
