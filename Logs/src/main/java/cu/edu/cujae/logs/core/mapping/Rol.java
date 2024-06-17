package cu.edu.cujae.logs.core.mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cu.edu.cujae.logs.core.dto.RolDto;
import cu.edu.cujae.logs.core.enums.RolEnums;
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
    @NotNull(message = "El identificador no puede ser null")
    @Column(name = "rolID", nullable = false, length = 40, unique = true,updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long uuid;

    @NotNull(message = "El rol no puede ser null")
    @NotBlank(message = "El rol no puede estar vacío")
    @Size(min = 4,max = 50,message = "El rol debe tener entre 4 y 50 caracteres")
    @Column(name = "rolNombre", nullable = false, length = 100, unique = true,updatable = true)
    private String rol;


    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private List<Usuario> usuarioList;

    public void setUsuarioList(List<Usuario> usuarioList) {
        usuarioList.forEach(s->{
            s.setRol(this);
        });
        this.usuarioList = usuarioList;
    }

    public Rol(RolEnums rolEnums){
        this.rol = rolEnums.getNombre();
    }

    public Rol(RolDto rolDto){
        this.rol = rolDto.getNombre();
    }

    public Rol(Optional<Rol> rolOptional){
        this.rol = rolOptional.get().getRol();
    }

    //Falta la unión con los privilegios
}
