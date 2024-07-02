package cu.edu.cujae.gestion.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cu.edu.cujae.gestion.core.mapping.AreaSalud;
import cu.edu.cujae.gestion.core.mapping.Municipio;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreaSaludDto {
    @NotNull(message = "El id del área de salud no puede ser nulo")
    private Long uuid;

    @Size(min = 4,max = 50,message = "El nombre de la provincia  debe estar  entre 4 y 50 caracteres")
    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    private Long id2;

    @NotNull(message = "El cuerpo geométrico no puede ser nulo")
    @JsonIgnore
    private Geometry geometry;

    @NotNull(message = "El centroide no puede ser nulo")
    @JsonIgnore
    private Geometry centroide;

    @DecimalMin(value = "0.00000000000000000000001",message = "El valor mínimo debe ser 0.00000000000000000000001")
    @NotNull(message = "El valor del área en kilómetros cuadrados no debe ser nulo")
    private Double areakm2;

    @Min(value = 1,message = "El mínimo de población, debe ser 1")
    @NotNull(message = "La población no puede ser nula")
    private Long poblacion;

    @DecimalMin(value = "0.0000000000000000001", message = "El valor mínimo es: 0.0000000000000000001")
    private Double distCentroProv;

    @DecimalMin(value = "0.0000000000000000001", message = "El valor mínimo es: 0.0000000000000000001")
    private Double distcentromunic;

    private String municipio;

    public AreaSaludDto(AreaSalud areaSalud){
        this.uuid = areaSalud.getUuid();
        this.nombre = areaSalud.getNombre();
        this.id2 = areaSalud.getId2();
        this.geometry = areaSalud.getGeometry();
        this.centroide = areaSalud.getCentroide();
        this.areakm2 = areaSalud.getAreakm2();
        this.poblacion = areaSalud.getPoblacion();
        this.distCentroProv = areaSalud.getDistCentroProv();
        this.distcentromunic = areaSalud.getDistcentromunic();
        this.municipio = areaSalud.getMunicipio().getNombre();
    }
}
