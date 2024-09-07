package cu.edu.cujae.logs.core.utils.enums;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public enum EstadoEnums {
    ACEPTADO("Aceptado","La petición a sido aceptada"),
    RECHAZADO("Rechazado","La petición a sido denegada");

    @NotNull(message = "El nombre del estado no puede ser null")
    @NotBlank(message = "El nombre del estado no puede estar vacío")
    @Size(min = 4,max = 100,message = "El nombre del estado debe estar entre 4 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La descrpción del Estado no puede ser null")
    @NotBlank(message = "La descrpción del Estado no puede estar vacío")
    @Size(min = 4,max = 100,message = "La descrpción del Estado debe estar entre 4 y 100 caracteres")
    private String descripcion;

    private EstadoEnums(String nombre, String descripcion) {
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
