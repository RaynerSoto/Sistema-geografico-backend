package cu.edu.cujae.logs.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.logs.core.mapping.Estado;
import cu.edu.cujae.logs.core.mapping.Registro;
import cu.edu.cujae.logs.core.mapping.Usuario;
import jakarta.persistence.*;
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
    @NotNull(message = "El usuario  no puede ser null")
    @NotBlank(message = "El usuario no puede estar vacío")
    private String usuario;

    @NotNull(message = "La actividad no puede ser null")
    @Size(min = 6,max = 100,message = "La actividad dedbe estar entre 6 y 100 caracteres")
    @NotBlank(message = "La actividad no puede estar vacío")
    private String actividad;

    @NotNull(message = "El IP no puede ser null")
    @Size(min = 7,max = 15,message = "La dirección IP con formato XXX.XXX.XXX es incorrecta")
    @NotBlank(message = "El IP no puede estar vacío")
    private String ip;

    @NotNull(message = "El nombre del PC no puede ser null")
    @Size(min = 1,message = "El nombre del PC debe tener mínimo 1 caracter")
    @NotBlank(message = "El nombre del PC no puede estar vacío")
    private String nombrePC;


    @NotNull(message = "El estado no puede ser null")
    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    public RegistroDto(Registro registro){
        this.uuid = registro.getUuid();
        this.usuario = registro.getUsuario().getUsername();
        this.actividad = registro.getActividad();
        this.ip = registro.getIp();
        this.nombrePC = registro.getNombrePC();
        this.estado = registro.getEstado().getNombre();
    }

}
