package cu.edu.cujae.gestion.core.dto;

import cu.edu.cujae.gestion.core.mapper.Geometria;
import cu.edu.cujae.gestion.core.mapper.Municipio;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MunicipioDto{

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

    @Min(value = 1,message = "El mínimo de población, debe ser 1")
    @NotNull(message = "La población no puede ser nula")
    private Long poblacion;

    @DecimalMin(value = "0.00000000000000000000001",message = "El valor mínimo debe ser 0.00000000000000000000001")
    @NotNull(message = "El valor del área en kilómetros cuadrados no debe ser nulo")
    private Double areakm2;

    @NotBlank(message = "Las siglas no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "Las siglas no puede ser nulo")
    private String siglas;

    @DecimalMin(value = "0.0000000000000000001", message = "El valor mínimo es: 0.0000000000000000001")
    private Double distCentroProv;

    @NotBlank(message = "La provinicia no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "La provicia no puede ser nulo")
    private String provincia;

    public MunicipioDto(Municipio municipio){
        this.uuid = municipio.getUuid();
        this.codigo = municipio.getCodigo();
        this.nombre = municipio.getNombre();
        this.poblacion = municipio.getPoblacion();
        this.areakm2 = municipio.getAreakm2();
        this.siglas = municipio.getSiglas();
        this.distCentroProv = municipio.getDistCentroProv();
        this.provincia = municipio.getProvincia().getNombre();
    }

    public MunicipioDto(Optional<Municipio> municipio){
        this.uuid = municipio.get().getUuid();
        this.codigo = municipio.get().getCodigo();
        this.nombre = municipio.get().getNombre();
        this.poblacion = municipio.get().getPoblacion();
        this.areakm2 = municipio.get().getAreakm2();
        this.siglas = municipio.get().getSiglas();
        this.distCentroProv = municipio.get().getDistCentroProv();
        this.provincia = municipio.get().getProvincia().getNombre();
    }

    public class MunicipioDtoGeometry extends Geometria{

        public MunicipioDtoGeometry(Municipio municipio){
            super(municipio.getGeometry(),municipio.getCentroide());
            uuid = municipio.getUuid();
            codigo = municipio.getCodigo();
            nombre = municipio.getNombre();
            poblacion = municipio.getPoblacion();
            areakm2 = municipio.getAreakm2();
            siglas = municipio.getSiglas();
            distCentroProv = municipio.getDistCentroProv();
            provincia = municipio.getProvincia().getNombre();
        }

        public MunicipioDtoGeometry(Optional<Municipio> municipio){
            super(municipio.get().getGeometry(),municipio.get().getCentroide());
            uuid = municipio.get().getUuid();
            codigo = municipio.get().getCodigo();
            nombre = municipio.get().getNombre();
            poblacion = municipio.get().getPoblacion();
            areakm2 = municipio.get().getAreakm2();
            siglas = municipio.get().getSiglas();
            distCentroProv = municipio.get().getDistCentroProv();
            provincia = municipio.get().getProvincia().getNombre();
        }
    }
}
