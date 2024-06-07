package cu.edu.cujae.core.enums;

public enum Estado {
    ACTIVO("Activo","Usuario acaba de loguearse en el sistema"),
    OPERATIVO("Operativo","El usuario ha realizado una acción en el sistema"),
    CERRADO("Cerrado","Usuario acaba de cerrar sesión en el sistema"),
    ERROR("Error","Error en la operación"),
    NO_AUTORIZADO("No Autorizado","El usuario no tiene autorización para la tarea actual");
    
    private String nombre;
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
