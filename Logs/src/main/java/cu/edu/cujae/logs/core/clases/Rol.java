package cu.edu.cujae.logs.core.clases;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Rol {

    @Id
    @NotNull(message = "El identificador no puede ser null")
    @NotBlank(message = "El identificador no puede estar vacío")
    @Column(name = "uuid", nullable = false, length = 40, unique = true,updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @NotNull(message = "El rol no puede ser null")
    @NotBlank(message = "El rol no puede estar vacío")
    @Size(min = 4,max = 50,message = "El rol debe tener entre 4 y 50 caracteres")
    @Column(name = "rol", nullable = false, length = 100, unique = true,updatable = true)
    private String rol;
}
