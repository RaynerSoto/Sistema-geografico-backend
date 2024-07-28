package cu.edu.cujae.gestion.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Time;

public class EntidadDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long uuid;

    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre de la entidad no puede ser null")
    @Size(min = 3,max = 100,message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    private String entidadMadre;

    @NotBlank(message = "El municipio no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El municipio no puede ser nulo")
    public String municipio;

    @NotNull(message = "La provincia no puede ser nulo")
    public String provincia;

    @NotBlank(message = "La calle principal de la entidad no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "La calle principal de la entidad no puede ser null")
    public String calle_principal;

    private String entrecalle1;

    private String entrecalle2;

    private String numeroCasa;

    private String localidad;

    private Time horario_entrada;

    private Time horario_salida;

    private Time horario_propuesto_entrada;

    private Time horario_propuesto_salida;

    private String datos;

    private Long zona_transporte;
}
