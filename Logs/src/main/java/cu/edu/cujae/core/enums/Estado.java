package cu.edu.cujae.core.enums;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public enum Estado {
    ACTIVO("Activo","Usuario acaba de loguearse en el sistema"),
    OPERATIVO("Operativo","El usuario ha realizado una acción en el sistema"),
    CERRADO("Cerrado","Usuario acaba de cerrar sesión en el sistema"),
    ERROR("Error","Error en la operación"),
    NO_AUTORIZADO("No Autorizado","El usuario no tiene autorización para la tarea actual");

    @NotNull(message = "El nombre del estado no puede ser null")
    @NotBlank(message = "El nombre del estado no puede estar vacío")
    @Size(min = 4,max = 100,message = "El nombre del estado debe estar entre 4 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La descrpción del Estado no puede ser null")
    @NotBlank(message = "La descrpción del Estado no puede estar vacío")
    @Size(min = 4,max = 100,message = "La descrpción del Estado debe estar entre 4 y 100 caracteres")
    private String descripcion;

    private Estado(String nombre,String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getDescripcion() {
        return descripcion;
    }

    public String getValueConvert(){
        return this.name().toString().replace("_"," ");
    }
}
