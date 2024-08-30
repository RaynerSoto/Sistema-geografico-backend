package cu.edu.cujae.gestion.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.gestion.core.abstractas.General;
import cu.edu.cujae.gestion.core.mapping.Entidad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntidadDto extends General {

    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre de la entidad no puede ser null")
    @Size(min = 3,max = 1000,message = "El nombre debe tener entre 3 y 1000 caracteres")
    private String nombre;

    private String entidadMadre;

    private String numeroCasa;

    private String localidad;

    private Time horario_entrada;

    private Time horario_salida;

    private Time horario_propuesto_entrada;

    private Time horario_propuesto_salida;

    private String datos;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long zona_transporte;

    public EntidadDto(Entidad entidad) {
        super(entidad.getUuid(), entidad.getMunicipio().getNombre(), entidad.getProvincia().getNombre(),entidad.getDireccion());
        this.nombre = entidad.getNombre();
        this.entidadMadre = entidad.getEntidadMadre();
        this.numeroCasa = entidad.getNumeroCasa();
        this.localidad = entidad.getLocalidad();
        this.horario_entrada = entidad.getHorario_entrada();
        this.horario_salida = entidad.getHorario_salida();
        this.datos = entidad.getDatos();
        this.zona_transporte = entidad.getZona_transporte();
        this.horario_propuesto_entrada = entidad.getHorario_propuesto_entrada();
        this.horario_propuesto_salida = entidad.getHorario_propuesto_salida();
    }

    public EntidadDto(Optional<Entidad> entidad) {
        super(entidad.get().getUuid(), entidad.get().getMunicipio().getNombre(), entidad.get().getProvincia().getNombre(),entidad.get().getDireccion());
        this.nombre = entidad.get().getNombre();
        this.entidadMadre = entidad.get().getEntidadMadre();
        this.numeroCasa = entidad.get().getNumeroCasa();
        this.localidad = entidad.get().getLocalidad();
        this.horario_entrada = entidad.get().getHorario_entrada();
        this.horario_salida = entidad.get().getHorario_salida();
        this.datos = entidad.get().getDatos();
        this.zona_transporte = entidad.get().getZona_transporte();
        this.horario_propuesto_entrada = entidad.get().getHorario_propuesto_entrada();
        this.horario_propuesto_salida = entidad.get().getHorario_propuesto_salida();
    }
}
