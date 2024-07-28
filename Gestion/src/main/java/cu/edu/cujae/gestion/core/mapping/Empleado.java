package cu.edu.cujae.gestion.core.mapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personal")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull(message = "El id del trabajador no puede ser nulo")
    @Column(name = "idpersonal", nullable = false, updatable = false, unique = true)
    private Long uuid;

    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre de la entidad no puede ser null")
    @Size(min = 3,max = 100,message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(name = "nombre",nullable = false,length = 100)
    private String nombre;

    @NotBlank(message = "El carnet de identidad no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El carnet de la entidad no puede ser null")
    @Size(min = 11,max = 11,message = "El carnet debe tener 11 caracteres")
    @Column(name = "ci",nullable = false, unique = true, length = 11)
    private String ci;

    @NotNull(message = "El municipio no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idmunicipio")
    public Municipio municipio;

    @NotNull(message = "La provincia no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprovincia")
    public Provincia provincia;

    @NotBlank(message = "La calle principal del trabajador no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "La calle principal del trabajador no puede ser null")
    @Column(name = "callePrincipal",nullable = false)
    public String calle_principal;

    @Column(name = "entrecalle1")
    private String entrecalle1;

    @Column(name = "entrecalle2")
    private String entrecalle2;

    @Column(name = "datosAdicionales")
    private String datos;

    @Column(name = "zona_transporte")
    private Long zona_transporte;

    @Column(name = "areaSalud")
    private Long areaSalud;

    @Column(name = "distcentroasalud")
    private Double distcentroasalud;

    @Column(name = "distcentrozonat")
    private Double distcentrozonat;

    @Column(name = "distcentromunic")
    private Double distcentromunic;

    @Column(name = "distcentroprov")
    private Double distcentroprov;

    @Column(name = "geolocalizacion",columnDefinition = "geometry")
    private Geometry geometry;

    @Column(name = "numero")
    private String numero;

    @Column(name = "localidad")
    private String localidad;

    @ManyToMany(mappedBy = "personal")
    private List<Entidad> entidades;
}
