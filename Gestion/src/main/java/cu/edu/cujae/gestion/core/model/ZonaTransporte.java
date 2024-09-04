package cu.edu.cujae.gestion.core.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Entity
@Table(name = "zona_transporte")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZonaTransporte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull(message = "El id de la zona de transporte no puede ser nulo")
    @Column(name = "idzona", nullable = false, updatable = false, unique = true)
    private Long uuid;

    @Column(name = "geom", nullable = false,columnDefinition = "geometry")
    @NotNull(message = "El cuerpo geométrico no puede ser nulo")
    private Geometry geometry;

    @Column(name = "direc_centrica", nullable = false)
    private String direc_centrica;

    @Column(name = "indice_acc")
    private int indice_acc;

    @DecimalMin(value = "0.00000000000000000000001",message = "El valor mínimo debe ser 0.00000000000000000000001")
    @NotNull(message = "El valor del área en kilómetros cuadrados no debe ser nulo")
    @Column(name = "areakm2")
    private Double areakm2;

    @Min(value = 1,message = "El mínimo de población, debe ser 1")
    @NotNull(message = "La población no puede ser nula")
    @Column(name = "poblacion")
    private Long poblacion;

    @NotNull(message = "El centroide no puede ser nulo")
    @Column(name = "centroide", nullable = false,columnDefinition = "geometry")
    private Geometry centroide;

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
