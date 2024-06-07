package cu.edu.cujae.core.enums;

public enum Estado {
    ACTIVO("Usuario acaba de loguearse en el sistema"),
    OPERATIVO("Usuario acaba de cerrar sesión en el sistema"),
    CERRADO("Error en la operación"),
    ERROR("El usuario no tiene autorización para la tarea actual"),
    NO_AUTORIZADO("El usuario ha realizado una acción en el sistema");

    private String descripcion;

    private Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    private String getDescripcion() {
        return descripcion;
    }

    public String getValueConvert(){
        return this.name().toString().replace("_"," ");
    }
}
