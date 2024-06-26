package cu.edu.cujae.logs.core.mapping;

import cu.edu.cujae.logs.core.utils.Validacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.MediaSize;
import java.util.Optional;

@Entity
@Data
@Table(name = "privilegiosRol")
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegioRol {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    @NotNull(message = "El id no puede ser null")
    private Long uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolID")
    @NotNull(message = "El rol no puede ser null")
    private Rol rol;


    @NotNull(message = "El privilegio no puede ser null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigoNombre")
    private Privilegio privilegioCodigo;

    public PrivilegioRol(Rol rol, Privilegio privilegioCodigo){
        this.rol = rol;
        this.privilegioCodigo = privilegioCodigo;
    }

    public PrivilegioRol(Optional<Rol> rol, Optional<Privilegio> privilegio) {
        this.rol = rol.get();
        this.privilegioCodigo = privilegio.get();
    }

    @PrePersist
    public void prePersist() {
        Validacion.validarUnsupportedOperationException(this);
    }

    @PreUpdate
    public void preUpdate() {
        Validacion.validarUnsupportedOperationException(this);
        if (this.uuid.equals(null)){
            throw new UnsupportedOperationException("No se puede modificar un id nulo");
        }
    }
}
