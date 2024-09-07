package cu.edu.cujae.gestion.core.dto;

import cu.edu.cujae.gestion.core.mapper.Geometria;
import cu.edu.cujae.gestion.core.mapper.ZonaTransporte;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZonaTransporteDto {
    @NotNull(message = "El id de la zona de transporte no puede ser nulo")
    private Long uuid;

    private String direc_centrica;

    private int indice_acc;

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

    public ZonaTransporteDto(ZonaTransporte zonaTransporte){
        this.uuid = zonaTransporte.getUuid();
        this.direc_centrica = zonaTransporte.getDirec_centrica();
        this.indice_acc = zonaTransporte.getIndice_acc();
        this.areakm2 = zonaTransporte.getAreakm2();
        this.poblacion = zonaTransporte.getPoblacion();
        this.distCentroProv = zonaTransporte.getDistCentroProv();
        this.distcentromunic = zonaTransporte.getDistcentromunic();
        this.municipio = zonaTransporte.getMunicipio().getNombre();
    }

    public ZonaTransporteDto(Optional<ZonaTransporte> zonaTransporte){
        this.uuid = zonaTransporte.get().getUuid();
        this.direc_centrica = zonaTransporte.get().getDirec_centrica();
        this.indice_acc = zonaTransporte.get().getIndice_acc();
        this.areakm2 = zonaTransporte.get().getAreakm2();
        this.poblacion = zonaTransporte.get().getPoblacion();
        this.distCentroProv = zonaTransporte.get().getDistCentroProv();
        this.distcentromunic = zonaTransporte.get().getDistcentromunic();
        this.municipio = zonaTransporte.get().getMunicipio().getNombre();
    }


    public class ZonaTransporteDtoGeoemtry extends Geometria{

        public ZonaTransporteDtoGeoemtry(ZonaTransporte zonaTransporte){
            super(zonaTransporte.getGeometry(),zonaTransporte.getCentroide());
            uuid = zonaTransporte.getUuid();
            direc_centrica = zonaTransporte.getDirec_centrica();
            indice_acc = zonaTransporte.getIndice_acc();
            areakm2 = zonaTransporte.getAreakm2();
            poblacion = zonaTransporte.getPoblacion();
            distCentroProv = zonaTransporte.getDistCentroProv();
            distcentromunic = zonaTransporte.getDistcentromunic();
            municipio = zonaTransporte.getMunicipio().getNombre();
        }

        public ZonaTransporteDtoGeoemtry(Optional<ZonaTransporte> zonaTransporte){
            super(zonaTransporte.get().getGeometry(),zonaTransporte.get().getCentroide());
            uuid = zonaTransporte.get().getUuid();
            direc_centrica = zonaTransporte.get().getDirec_centrica();
            indice_acc = zonaTransporte.get().getIndice_acc();
            areakm2 = zonaTransporte.get().getAreakm2();
            poblacion = zonaTransporte.get().getPoblacion();
            distCentroProv = zonaTransporte.get().getDistCentroProv();
            distcentromunic = zonaTransporte.get().getDistcentromunic();
            municipio = zonaTransporte.get().getMunicipio().getNombre();
        }
    }
}
