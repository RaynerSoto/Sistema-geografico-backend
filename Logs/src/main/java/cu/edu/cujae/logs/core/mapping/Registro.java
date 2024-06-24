package cu.edu.cujae.logs.core.mapping;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "registros")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "registroID", unique = true, nullable = false)
    private Long uuid;

    @NotNull(message = "El usuario  no puede ser null")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotBlank(message = "El usuario no puede estar vacío")
    @JoinColumn(name = "usuarioID")
    private Usuario usuario;

    @NotNull(message = "La actividad no puede ser null")
    @Size(min = 6,max = 100,message = "La actividad dedbe estar entre 6 y 100 caracteres")
    @NotBlank(message = "La actividad no puede estar vacío")
    @Column(name = "actividad", nullable = false, length = 100)
    private String actividad;

    @NotNull(message = "El IP no puede ser null")
    @Size(min = 7,max = 15,message = "La dirección IP con formato XXX.XXX.XXX es incorrecta")
    @NotBlank(message = "El IP no puede estar vacío")
    @Column(name = "direccion_IP", nullable = false, length = 15)
    private String ip;

    @NotNull(message = "El nombre del PC no puede ser null")
    @Size(min = 1,message = "El nombre del PC debe tener mínimo 1 caracter")
    @NotBlank(message = "El nombre del PC no puede estar vacío")
    @Column(name = "nombrePC", nullable = false, length = 200)
    private String nombrePC;


    @NotNull(message = "El estado no puede ser null")
    @NotBlank(message = "El estado no puede estar vacío")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estadoID", nullable = false)
    private Estado estado;

    @PreUpdate
    public void preUpdate() {
        throw new UnsupportedOperationException("Operacion de modificación no permitida");
    }

    @PreRemove
    public void preRemove() {
        throw new UnsupportedOperationException("Operacion de eliminación no permitida");
    }
}
