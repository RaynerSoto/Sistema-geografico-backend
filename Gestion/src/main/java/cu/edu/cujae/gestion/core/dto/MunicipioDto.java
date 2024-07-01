package cu.edu.cujae.gestion.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cu.edu.cujae.gestion.core.mapping.Municipio;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDto {


    @NotNull(message = "El id de provincia no puede ser nulo")
    private Long uuid;

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
    @JsonIgnore
    private Geometry centroide;

    @DecimalMin(value = "0.0000000000000000001", message = "El valor mínimo es: 0.0000000000000000001")
    private Double distCentroProv;

    @NotBlank(message = "La provinicia no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "La provicia no puede ser nulo")
    private String provincia;

    public MunicipioDto(Municipio municipio){
        this.uuid = municipio.getUuid();
        this.codigo = municipio.getCodigo();
        this.nombre = municipio.getNombre();
        this.geometry = municipio.getGeometry();
        this.poblacion = municipio.getPoblacion();
        this.areakm2 = municipio.getAreakm2();
        this.siglas = municipio.getSiglas();
        this.centroide = municipio.getCentroide();
        this.distCentroProv = municipio.getDistCentroProv();
        this.provincia = municipio.getProvincia().getNombre();
    }
}
