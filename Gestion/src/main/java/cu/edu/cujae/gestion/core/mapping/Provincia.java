package cu.edu.cujae.gestion.core.mapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "provincias")
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull(message = "El id de provincia no puede ser nulo")
    @Column(name = "idprovincia", nullable = false, updatable = false, unique = true)
    private int uuid;

    @Size(min = 2,max = 6,message = "El código de la provincia  debe estar  entre 2 y 6 caracteres")
    @NotBlank(message = "El código no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El código no puede ser nulo")
    @Column(name = "codigo", nullable = false, updatable = false, unique = true)
    private String codigo;

    @Size(min = 4,max = 50,message = "El nombre de la provincia  debe estar  entre 4 y 50 caracteres")
    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre no puede ser nulo")
    @Column(name = "nombre", nullable = false, updatable = false, unique = true)
    private String nombre;

    @Column(name = "geom", nullable = false, updatable = false,columnDefinition = "geometry")
    @NotNull(message = "El cuerpo geométrico no puede ser nulo")
    private Geometry geometry;

    @Min(value = 1,message = "El mínimo de población, debe ser 1")
    @NotNull(message = "La población no puede ser nula")
    @Column(name = "poblacion", nullable = false)
    private Long poblacion;

    @DecimalMin(value = "0.00000000000000000000001",message = "El valor mínimo debe ser 0.00000000000000000000001")
    @NotNull(message = "El valor del área en kilómetros cuadrados no debe ser nulo")
    @Column(name = "areakm2", nullable = false)
    private Double areakm2;

    @NotBlank(message = "Las siglas no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "Las siglas no puede ser nulo")
    @Column(name = "siglas", nullable = false)
    private String siglas;

    @NotNull(message = "El centroide no puede ser nulo")
    @Column(name = "centroide", nullable = false,columnDefinition = "geometry")
    private Geometry centroide;

    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Municipio> listadoMunicipios;

    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Entidad> entidades;

    @OneToMany(mappedBy = "provincia", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Empleado> empleados;
}
