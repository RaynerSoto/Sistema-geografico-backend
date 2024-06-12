package cu.edu.cujae.logs.core.clases;

import cu.edu.cujae.logs.core.enums.PrivilegioEnums;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "privilegios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Privilegio {
    @Id()
    @NotNull(message = "El id no puede ser nulo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "privilegiosID",nullable = false,unique = true, updatable = false)
    private Long uuid;

    @Column(name = "codigo",nullable = false,unique = true)
    private String codigo;

    @Column(name = "descripcion",nullable = false,unique = true)
    private String descripcion;

    public Privilegio(PrivilegioEnums privilegioEnums) {
        this.codigo = privilegioEnums.getCodigo();
        this.descripcion = privilegioEnums.getDescripcion();
    }
}
