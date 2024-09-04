package cu.edu.cujae.gestion.core.dto;

import cu.edu.cujae.gestion.core.model.Geometria;
import cu.edu.cujae.gestion.core.model.AreaSalud;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

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
        this.areakm2 = areaSalud.getAreakm2();
        this.poblacion = areaSalud.getPoblacion();
        this.distCentroProv = areaSalud.getDistCentroProv();
        this.distcentromunic = areaSalud.getDistcentromunic();
        this.municipio = areaSalud.getMunicipio().getNombre();
    }

    public AreaSaludDto(Optional<AreaSalud> areaSalud){
        uuid = areaSalud.get().getUuid();
        nombre = areaSalud.get().getNombre();
        id2 = areaSalud.get().getId2();
        areakm2 = areaSalud.get().getAreakm2();
        poblacion = areaSalud.get().getPoblacion();
        distCentroProv = areaSalud.get().getDistCentroProv();
        distcentromunic = areaSalud.get().getDistcentromunic();
        municipio = areaSalud.get().getMunicipio().getNombre();
    }


    public class AreaSaludDtoGeometry extends Geometria {

        public AreaSaludDtoGeometry(AreaSalud areaSalud){
            super(areaSalud.getGeometry(),areaSalud.getGeometry());
            uuid = areaSalud.getUuid();
            nombre = areaSalud.getNombre();
            id2 = areaSalud.getId2();
            areakm2 = areaSalud.getAreakm2();
            poblacion = areaSalud.getPoblacion();
            distCentroProv = areaSalud.getDistCentroProv();
            distcentromunic = areaSalud.getDistcentromunic();
            municipio = areaSalud.getMunicipio().getNombre();
        }

        public AreaSaludDtoGeometry(Optional<AreaSalud> areaSalud){
            super(areaSalud.get().getGeometry(),areaSalud.get().getGeometry());
            uuid = areaSalud.get().getUuid();
            nombre = areaSalud.get().getNombre();
            id2 = areaSalud.get().getId2();
            areakm2 = areaSalud.get().getAreakm2();
            poblacion = areaSalud.get().getPoblacion();
            distCentroProv = areaSalud.get().getDistCentroProv();
            distcentromunic = areaSalud.get().getDistcentromunic();
            municipio = areaSalud.get().getMunicipio().getNombre();
        }
    }
}
