package cu.edu.cujae.gestion.core.mappingSecurity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sexo{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "sexoID",unique = true, nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "El nombre del sexo no puede ser null")
    @Size(min = 4,max = 100,message = "El nombre del sexo debe estar entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del sexo no puede estar vacío")
    @Column(name = "nombre",nullable = false, length = 50,unique = true)
    private String nombre;

    @NotNull(message = "La sigla no puede ser null")
    @NotBlank(message = "La sigla no puede estar vacío")
    @Size(min = 1,max = 5,message = "Las sigla solamente tienen entre 1 y 5 caracteres")
    @Column(name = "sigla",nullable = false, length = 5,unique = true)
    private String sigla;

    @OneToMany(mappedBy = "sexo",cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Usuario> usuarioList;

    public Sexo(Optional<Sexo> sexo) {
        this.id = sexo.get().getId();
        this.nombre = sexo.get().nombre;
        this.sigla = sexo.get().sigla;
    }
}
