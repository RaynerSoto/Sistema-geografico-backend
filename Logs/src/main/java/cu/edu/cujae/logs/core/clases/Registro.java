package cu.edu.cujae.logs.core.clases;

import cu.edu.cujae.logs.core.enums.EstadoEnums;
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
    @NotNull(message = "El identificador no puede ser null")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "registroID", unique = true, nullable = false)
    private Long uuid;

    @NotNull(message = "El id de usuario  no puede ser null")
    @NotBlank(message = "El id de usuario no puede estar vacío")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuarioID")
    private Usuario usuario;

    @NotNull(message = "La actividad no puede ser null")
    @NotBlank(message = "La actividad no puede estar vacío")
    @Size(min = 6,max = 100,message = "La actividad dedbe estar entre 6 y 100 caracteres")
    @Column(name = "actividad", nullable = false, length = 100)
    private String actividad;

    @NotNull(message = "El IP no puede ser null")
    @NotBlank(message = "El IP no puede estar vacío")
    @Size(min = 7,max = 15,message = "La dirección IP con formato XXX.XXX.XXX es incorrecta")
    @Column(name = "direccion_IP", nullable = false, length = 15)
    private String ip;

    @NotNull(message = "El nombre del PC no puede ser null")
    @NotBlank(message = "El nombre del PC no puede estar vacío")
    @Size(min = 1,message = "El nombre del PC debe tener mínimo 1 caracter")
    @Column(name = "nombrePC", nullable = false, length = 200)
    private String nombrePC;


    @NotNull(message = "El estado no puede ser null")
    @NotBlank(message = "El estado no puede estar vacío")
    @Enumerated(EnumType.ORDINAL)
    private EstadoEnums estadoEnums;
}
