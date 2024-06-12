package cu.edu.cujae.logs.core.clases;

import cu.edu.cujae.logs.core.enums.Sexo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "usuarios")
public class Usuario {
    @Id
    @NotNull(message = "El id no puede ser nulo")
    @NotBlank(message = "El id no puede ser vacío")
    @Column(name = "uuid",nullable = false, length = 36, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    @NotNull(message = "El nombre de usuario no puede ser nulo")
    @NotBlank(message = "El nombre de usuario no puede ser vacío")
    @Size(min = 4,max = 50,message = "El nombre de usuario se encuentra en un rango de 4 y 50 caracteres")
    private String username;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @NotBlank(message = "El nombre completo no puede ser vacío")
    @Size(min = 2,max = 100,message = "El nombre completo debe tener entre 2 y 100 caracteres")
    private String name;

    @NotNull(message = "El correo no puede ser nulo")
    @NotBlank(message = "El correo no puede ser vacío")
    @Email(message = "Correo no válido")
    private String email;

    @NotNull(message = "El rol no puede ser nulo")
    @NotBlank(message = "El rol no puede ser vacío")
    @Transient
    private Rol rol;

    @NotNull(message = "El sexo no puede ser nulo")
    @NotBlank(message = "El sexo no puede ser vacío")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(name = "activo", nullable = false)
    private boolean activo;
}
