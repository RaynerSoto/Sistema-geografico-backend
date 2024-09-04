package cu.edu.cujae.gestion.core.dto.empleadoDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import cu.edu.cujae.gestion.core.model.Empleado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDtoRegular extends EmpleadoDto{
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> entidades;

    public EmpleadoDtoRegular(Empleado empleadoDto) {
        super(empleadoDto);
        entidades = empleadoDto.getEntidades().stream()
                .map(s -> s.getNombre()).toList();
    }

    public EmpleadoDtoRegular(EmpleadoDto empleadoDto) {
        super(empleadoDto);
    }

    public EmpleadoDtoRegular(EmpleadoDto empleadoDto, List<String> entidades) {
        super(empleadoDto);
        this.entidades = entidades;
    }

    public EmpleadoDtoRegular(Empleado empleado,List<String> entidades) {
        super(empleado);
        this.entidades = entidades;
    }
}
