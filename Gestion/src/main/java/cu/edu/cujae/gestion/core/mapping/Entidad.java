package cu.edu.cujae.gestion.core.mapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entidades")
public class Entidad {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotNull(message = "El id de la entidad no puede ser nulo")
    @Column(name = "identidad", nullable = false, updatable = false, unique = true)
    private Long uuid;

    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre de la entidad no puede ser null")
    @Size(min = 3,max = 100,message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(name = "nombre",nullable = false, unique = true,length = 100)
    private String nombre;

    @Column(name = "entidadmadre",length = 100)
    private String entidadMadre;

    @NotNull(message = "El municipio no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idmunicipio")
    public Municipio municipio;

    @NotNull(message = "La provincia no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idprovincia")
    public Provincia provincia;

    @NotBlank(message = "La dirección de la entidad no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "La dirección de la entidad no puede ser null")
    @Column(name = "direccion",nullable = false)
    public String direccion;


}
