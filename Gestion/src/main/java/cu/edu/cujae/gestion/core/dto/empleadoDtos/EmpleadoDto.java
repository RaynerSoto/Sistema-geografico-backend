package cu.edu.cujae.gestion.core.dto.empleadoDtos;
import cu.edu.cujae.gestion.core.dto.abstractas.General;
import cu.edu.cujae.gestion.core.model.Empleado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDto extends General {

    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre de la entidad no puede ser null")
    @Size(min = 3,max = 100,message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El carnet de identidad no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El carnet de la entidad no puede ser null")
    @Size(min = 11,max = 11,message = "El carnet debe tener 11 caracteres")
    @Pattern(regexp = "^[0-9]{2}([0]?[0-9]|[1]?[0-2])([0-2]?[0-9]|[3]?[0-1])\\d{5}$",
            message = "No se cumple los valores del carnet de identidad")
    private String ci;

    private String datos;

    private String numero;

    private String localidad;

    public EmpleadoDto(Empleado empleado){
        super(empleado.getUuid(), empleado.getMunicipio().getNombre(), empleado.getProvincia().getNombre(),empleado.getDireccion());
        this.nombre = empleado.getNombre();
        this.ci = empleado.getCi();
        this.datos = empleado.getDatos();
        this.numero = empleado.getNumero();
        this.localidad = empleado.getLocalidad();
    }

    public EmpleadoDto(EmpleadoDto empleado){
        super(empleado.getUuid(), empleado.getMunicipio(), empleado.getProvincia(),empleado.getDireccion());
        this.nombre = empleado.getNombre();
        this.ci = empleado.getCi();
        this.datos = empleado.getDatos();
        this.numero = empleado.getNumero();
        this.localidad = empleado.getLocalidad();
    }
}
