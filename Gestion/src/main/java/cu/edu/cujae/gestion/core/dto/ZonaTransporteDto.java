package cu.edu.cujae.gestion.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cu.edu.cujae.gestion.core.mapping.ZonaTransporte;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZonaTransporteDto {


    @NotNull(message = "El id de la zona de transporte no puede ser nulo")
    private Long uuid;

    @NotNull(message = "El cuerpo geométrico no puede ser nulo")
    @JsonIgnore
    private Geometry geometry;

    private String direc_centrica;

    private int indice_acc;

    @DecimalMin(value = "0.00000000000000000000001",message = "El valor mínimo debe ser 0.00000000000000000000001")
    @NotNull(message = "El valor del área en kilómetros cuadrados no debe ser nulo")
    private Double areakm2;

    @Min(value = 1,message = "El mínimo de población, debe ser 1")
    @NotNull(message = "La población no puede ser nula")
    private Long poblacion;

    @NotNull(message = "El centroide no puede ser nulo")
    @JsonIgnore
    private Geometry centroide;

    @DecimalMin(value = "0.0000000000000000001", message = "El valor mínimo es: 0.0000000000000000001")
    private Double distCentroProv;

    @DecimalMin(value = "0.0000000000000000001", message = "El valor mínimo es: 0.0000000000000000001")
    private Double distcentromunic;

    private String municipio;


    public ZonaTransporteDto(ZonaTransporte zonaTransporte){
        this.uuid = zonaTransporte.getUuid();
        this.geometry = zonaTransporte.getGeometry();
        this.direc_centrica = zonaTransporte.getDirec_centrica();
        this.indice_acc = zonaTransporte.getIndice_acc();
        this.areakm2 = zonaTransporte.getAreakm2();
        this.poblacion = zonaTransporte.getPoblacion();
        this.centroide = zonaTransporte.getCentroide();
        this.distCentroProv = zonaTransporte.getDistCentroProv();
        this.distcentromunic = zonaTransporte.getDistcentromunic();
        this.municipio = zonaTransporte.getMunicipio().getNombre();
    }
}
