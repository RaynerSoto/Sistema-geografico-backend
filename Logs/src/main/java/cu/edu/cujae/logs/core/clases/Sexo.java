package cu.edu.cujae.logs.core.clases;

import cu.edu.cujae.logs.core.enums.SexoEnums;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sexos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sexo {
    @Id
    @NotNull(message = "El id no puede ser nulo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "sexoID",unique = true, nullable = false,updatable = false,insertable = false)
    private Long id;

    @NotNull(message = "El nombre del sexo no puede ser null")
    @NotBlank(message = "El nombre del sexo no puede estar vacío")
    @Size(min = 4,max = 100,message = "El nombre del sexo debe estar entre 4 y 100 caracteres")
    @Column(name = "nombre",nullable = false, length = 50,unique = true)
    private String nombre;

    @NotNull(message = "La sigla no puede ser null")
    @NotBlank(message = "La sigla no puede estar vacío")
    @Size(min = 1,max = 5,message = "Las sigla solamente tienen entre 1 y 5 caracteres")
    @Column(name = "sigla",nullable = false, length = 5,unique = true)
    private String sigla;

    public Sexo (SexoEnums sexoEnums){
        this.nombre = sexoEnums.getNombre();
        this.sigla = sexoEnums.getSigla();
    }
}
