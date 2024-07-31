package cu.edu.cujae.gestion.core.dto.empleadoDtos;

import cu.edu.cujae.gestion.core.mapping.Empleado;

import java.util.List;

public class EmpleadoDtoRegular extends EmpleadoDto{
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
