package cu.edu.cujae.gestion.core.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String usuario;

    @Size(min = 6,max = 100,message = "La actividad dedbe estar entre 6 y 100 caracteres")
    @NotBlank(message = "La actividad no puede estar vacío")
    @NotNull(message = "La actividad no puede ser null")
    private String actividad;

    @Size(min = 7,max = 15,message = "La dirección IP con formato XXX.XXX.XXX es incorrecta")
    @NotBlank(message = "El IP no puede estar vacío")
    @NotNull(message = "El IP no puede ser null")
    private String ip;

    @NotNull(message = "El estado no puede ser null")
    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String fechaCreacion;

    public RegistroDto(String actividad) {
        this.actividad = actividad;
    }

    public RegistroDto(String username, String actividad, String ip, String estado) {
        this.usuario = username;
        this.actividad = actividad;
        this.ip = ip;
        this.estado = estado;
    }
}
