package cu.edu.cujae.logs.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.logs.core.mapping.Registro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long uuid;

    //Referencia al nombre de usuario
    @NotBlank(message = "El usuario no puede estar vacío")
    @NotNull(message = "El usuario  no puede ser null")
    private String usuario;

    @Size(min = 6,max = 100,message = "La actividad dedbe estar entre 6 y 100 caracteres")
    @NotBlank(message = "La actividad no puede estar vacío")
    @NotNull(message = "La actividad no puede ser null")
    private String actividad;

    @Size(min = 7,max = 15,message = "La dirección IP con formato XXX.XXX.XXX es incorrecta")
    @NotBlank(message = "El IP no puede estar vacío")
    @NotNull(message = "El IP no puede ser null")
    private String ip;


    @Size(min = 1,message = "El nombre del PC debe tener mínimo 1 caracter")
    @NotBlank(message = "El nombre del PC no puede estar vacío")
    @NotNull(message = "El nombre del PC no puede ser null")
    private String nombrePC;


    @NotNull(message = "El estado no puede ser null")
    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    public RegistroDto(Registro registro){
        this.uuid = registro.getUuid();
        this.usuario = registro.getNombreUsuario().getUsername();
        this.actividad = registro.getActividad();
        this.ip = registro.getIp();
        this.nombrePC = registro.getNombrePC();
        this.estado = registro.getEstado().getNombre();
    }

}
