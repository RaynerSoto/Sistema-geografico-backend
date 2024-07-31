package cu.edu.cujae.gestion.core.dto.empleadoDtos;

import cu.edu.cujae.gestion.core.mapping.Empleado;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoDtoInsert extends EmpleadoDto{
    private String entidad;

    public EmpleadoDtoInsert(EmpleadoDto empleadoDto,String entidad){
        super(empleadoDto);
        this.entidad = entidad;
    }

    public EmpleadoDtoInsert(Empleado empleado,String entidad){
        super(empleado);
        this.entidad = entidad;
    }


}
