package cu.edu.cujae.logs.core.mapper;

import cu.edu.cujae.logs.core.dto.RolDto;
import cu.edu.cujae.logs.core.utils.enums.RolEnums;
import cu.edu.cujae.logs.core.utils.Validacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Rol {

    @Id
    @Column(name = "rolID", nullable = false, length = 40, unique = true,updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long uuid;

    @NotNull(message = "El rol no puede ser null")
    @Size(min = 4,max = 50,message = "El rol debe tener entre 4 y 50 caracteres")
    @NotBlank(message = "El rol no puede estar vacío")
    @Column(name = "rolNombre", nullable = false, length = 100, unique = true,updatable = true)
    private String rol;


    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Usuario> usuarioList;

    public Rol(RolEnums rolEnums){
        this.rol = rolEnums.getNombre();
    }

    public Rol(RolDto rolDto){
        this.uuid = rolDto.getId();
        this.rol = rolDto.getNombre();
    }

    public Rol(Optional<Rol> rolOptional){
        this.uuid = rolOptional.get().getUuid();
        this.rol = rolOptional.get().getRol();
    }

    //Falta la unión con los privilegios

    @PrePersist
    public void prePersist(){
        Validacion.validarUnsupportedOperationException(this);
    }


    @PreRemove
    public void preRemove(){
        if (this.usuarioList.size() != 0)
            throw new UnsupportedOperationException("El rol se encuentra asignado a un usuario. Eliminalo para proseguir");
    }

    @PreUpdate
    public void preUpdate(){
        prePersist();
        if (this.uuid.equals(null) == true){
            throw new UnsupportedOperationException("El ID del rol a modificar no puede ser nulo");
        }
    }
}
