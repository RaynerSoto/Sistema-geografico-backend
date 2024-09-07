package cu.edu.cujae.logs.core.mapper;

import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.utils.Validacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

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

    @Column(name = "idusuario")
    private Long idUsuario;

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

    @NotNull(message = "El estado no puede ser null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estadoID")
    private Estado estado;

    @NotNull(message = "La fecha de creación no puede ser null")
    @Column(name = "fecha",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp fecha;

    public Registro(Registro registro) {
        this.uuid = registro.getUuid();
        this.idUsuario = registro.getIdUsuario();
        this.actividad = registro.getActividad();
        this.ip = registro.getIp();
        this.estado = registro.getEstado();
        this.fecha = Timestamp.valueOf(LocalDateTime.now());
    }

    public Registro(RegistroDto registroDto, Usuario nombreUsuario, Estado estado) {
        this.uuid = registroDto.getUuid();
        this.idUsuario = nombreUsuario.getUuid();
        this.actividad = registroDto.getActividad();
        this.ip = registroDto.getIp();
        this.estado = estado;
        this.fecha = Timestamp.valueOf(LocalDateTime.now());
    }

    public Registro(RegistroDto registroDto, Estado estado) {
        this.uuid = registroDto.getUuid();
        this.idUsuario = null;
        this.actividad = registroDto.getActividad();
        this.ip = registroDto.getIp();
        this.estado = estado;
        this.fecha = Timestamp.valueOf(LocalDateTime.now());
    }

    public Registro(Optional<Registro> registro) {
        this.uuid = registro.get().getUuid();
        this.idUsuario = registro.get().getIdUsuario();
        this.actividad = registro.get().getActividad();
        this.ip = registro.get().getIp();
        this.estado = registro.get().getEstado();
        this.fecha = Timestamp.valueOf(LocalDateTime.now());
    }

    public Registro(RegistroDto registroDto,Long idUsuario,Estado Estado){
        this.uuid = registroDto.getUuid();
        this.idUsuario = idUsuario;
        this.actividad = registroDto.getActividad();
        this.ip = registroDto.getIp();
        this.estado = Estado;
        this.fecha = Timestamp.valueOf(LocalDateTime.now());
    }

    @PrePersist
    public void prePersist() {
        Validacion.validarUnsupportedOperationException(this);
    }

    @PreUpdate
    public void preUpdate() {
        throw new UnsupportedOperationException("Operacion de modificación no permitida");
    }

    @PreRemove
    public void preRemove() {
        throw new UnsupportedOperationException("Operacion de eliminación no permitida");
    }
}
