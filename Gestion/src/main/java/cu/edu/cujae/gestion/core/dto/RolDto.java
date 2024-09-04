package cu.edu.cujae.gestion.core.dto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolDto {

    @Id
    @Column(name = "rolID", nullable = false, length = 40, unique = true,updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long uuid;

    @NotNull(message = "El rol no puede ser null")
    @Size(min = 4,max = 50,message = "El rol debe tener entre 4 y 50 caracteres")
    @NotBlank(message = "El rol no puede estar vacío")
    @Column(name = "rolNombre", nullable = false, length = 100, unique = true,updatable = true)
    private String rol;

    public RolDto(Optional<RolDto> rolOptional){
        this.uuid = rolOptional.get().getUuid();
        this.rol = rolOptional.get().getRol();
    }
}
