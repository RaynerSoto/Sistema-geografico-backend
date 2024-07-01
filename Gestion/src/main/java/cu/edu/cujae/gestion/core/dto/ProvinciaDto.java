package cu.edu.cujae.gestion.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.gestion.core.mapping.Provincia;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinciaDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "El id de provincia no puede ser nulo")
    private int uuid;

    @Size(min = 2,max = 6,message = "El código de la provincia  debe estar  entre 2 y 6 caracteres")
    @NotBlank(message = "El código no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El código no puede ser nulo")
    private String codigo;

    @Size(min = 4,max = 50,message = "El nombre de la provincia  debe estar  entre 4 y 50 caracteres")
    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotNull(message = "El cuerpo geométrico no puede ser nulo")
    @JsonIgnore
    private Geometry geometry;

    @Min(value = 1,message = "El mínimo de población, debe ser 1")
    @NotNull(message = "La población no puede ser nula")
    private Long poblacion;

    @DecimalMin(value = "0.00000000000000000000001",message = "El valor mínimo debe ser 0.00000000000000000000001")
    @NotNull(message = "El valor del área en kilómetros cuadrados no debe ser nulo")
    private Double areakm2;

    @NotBlank(message = "Las siglas no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "Las siglas no puede ser nulo")
    private String siglas;

    @NotNull(message = "El centroide no puede ser nulo")
    private Geometry centroide;

    public ProvinciaDto(Provincia provincia){
        this.uuid = provincia.getUuid();
        this.codigo = provincia.getCodigo();
        this.nombre = provincia.getNombre();
        this.geometry = provincia.getGeometry();
        this.poblacion = provincia.getPoblacion();
        this.areakm2 = provincia.getAreakm2();
        this.siglas = provincia.getSiglas();
        this.centroide = provincia.getCentroide();
    }

    public ProvinciaDto(Optional<Provincia> provincia){
        this.uuid = provincia.get().getUuid();
        this.codigo = provincia.get().getCodigo();
        this.nombre = provincia.get().getNombre();
        this.geometry = provincia.get().getGeometry();
        this.poblacion = provincia.get().getPoblacion();
        this.areakm2 = provincia.get().getAreakm2();
        this.siglas = provincia.get().getSiglas();
        this.centroide = provincia.get().getCentroide();
    }

}
