package cu.edu.cujae.gestion.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.gestion.core.mapper.Geometria;
import cu.edu.cujae.gestion.core.mapper.Provincia;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinciaDto{

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

    @Min(value = 1,message = "El mínimo de población, debe ser 1")
    @NotNull(message = "La población no puede ser nula")
    private Long poblacion;

    @DecimalMin(value = "0.00000000000000000000001",message = "El valor mínimo debe ser 0.00000000000000000000001")
    @NotNull(message = "El valor del área en kilómetros cuadrados no debe ser nulo")
    private Double areakm2;

    @NotBlank(message = "Las siglas no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "Las siglas no puede ser nulo")
    private String siglas;

    public ProvinciaDto(Provincia provincia){
        this.uuid = provincia.getUuid();
        this.codigo = provincia.getCodigo();
        this.nombre = provincia.getNombre();
        this.poblacion = provincia.getPoblacion();
        this.areakm2 = provincia.getAreakm2();
        this.siglas = provincia.getSiglas();
    }

    public ProvinciaDto(Optional<Provincia> provincia){
        this.uuid = provincia.get().getUuid();
        this.codigo = provincia.get().getCodigo();
        this.nombre = provincia.get().getNombre();
        this.poblacion = provincia.get().getPoblacion();
        this.areakm2 = provincia.get().getAreakm2();
        this.siglas = provincia.get().getSiglas();
    }

    public class ProvinciaDtoGeometry extends Geometria{

        public ProvinciaDtoGeometry(Provincia provincia){
            super(provincia.getGeometry(),provincia.getCentroide());
            uuid = provincia.getUuid();
            codigo = provincia.getCodigo();
            nombre = provincia.getNombre();
            poblacion = provincia.getPoblacion();
            areakm2 = provincia.getAreakm2();
            siglas = provincia.getSiglas();
        }

        public ProvinciaDtoGeometry(Optional<Provincia> provincia){
            super(provincia.get().getGeometry(),provincia.get().getCentroide());
            uuid = provincia.get().getUuid();
            codigo = provincia.get().getCodigo();
            nombre = provincia.get().getNombre();
            poblacion = provincia.get().getPoblacion();
            areakm2 = provincia.get().getAreakm2();
            siglas = provincia.get().getSiglas();
        }
    }
}
