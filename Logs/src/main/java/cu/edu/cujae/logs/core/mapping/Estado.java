package cu.edu.cujae.logs.core.mapping;

import cu.edu.cujae.logs.core.enums.EstadoEnums;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "estados")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "estadoID", nullable = false, updatable = false, unique = true)
    private Long uuid;

    @NotNull(message = "El nombre del estado no puede ser null")
    @Size(min = 4,max = 100,message = "El nombre del estado debe estar entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del estado no puede estar vacío")
    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;

    @NotNull(message = "La descrpción del Estado no puede ser null")
    @Size(min = 4,max = 100,message = "La descrpción del Estado debe estar entre 4 y 100 caracteres")
    @NotBlank(message = "La descrpción del Estado no puede estar vacío")
    @Column(name = "descripcion", nullable = false, length = 100, unique = true)
    private String descripcion;

    @OneToMany(mappedBy = "estado",fetch = FetchType.LAZY)
    private List<Registro> registroList;

    public Estado(EstadoEnums estadoEnums){
        this.nombre = estadoEnums.getNombre();
        this.descripcion = estadoEnums.getDescripcion();
    }
}
