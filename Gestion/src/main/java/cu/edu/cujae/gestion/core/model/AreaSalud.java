package cu.edu.cujae.gestion.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "area_salud")
public class AreaSalud {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull(message = "El id del área de salud no puede ser nulo")
    @Column(name = "idareasalud", nullable = false, updatable = false, unique = true)
    private Long uuid;

    @Size(min = 4,max = 50,message = "El nombre de la provincia  debe estar  entre 4 y 50 caracteres")
    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre no puede ser nulo")
    @Column(name = "nombre", nullable = false, updatable = false, unique = true)
    private String nombre;

    @NotNull(message = "El id2 del área de salud no puede ser nulo")
    @Column(name = "id2")
    private Long id2;

    @Column(name = "geom", nullable = false, updatable = false,columnDefinition = "geometry")
    @NotNull(message = "El cuerpo geométrico no puede ser nulo")
    private Geometry geometry;

    @NotNull(message = "El centroide no puede ser nulo")
    @Column(name = "centroide", nullable = false,columnDefinition = "geometry")
    private Geometry centroide;

    @DecimalMin(value = "0.00000000000000000000001",message = "El valor mínimo debe ser 0.00000000000000000000001")
    @NotNull(message = "El valor del área en kilómetros cuadrados no debe ser nulo")
    @Column(name = "areakm2")
    private Double areakm2;

    @Min(value = 1,message = "El mínimo de población, debe ser 1")
    @NotNull(message = "La población no puede ser nula")
    @Column(name = "poblacion")
    private Long poblacion;

    @Column(name = "distcentroprov")
    @DecimalMin(value = "0.0000000000000000001", message = "El valor mínimo es: 0.0000000000000000001")
    private Double distCentroProv;

    @Column(name = "distcentromunic")
    @DecimalMin(value = "0.0000000000000000001", message = "El valor mínimo es: 0.0000000000000000001")
    private Double distcentromunic;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "idmunicipio")
    private Municipio municipio;
}
