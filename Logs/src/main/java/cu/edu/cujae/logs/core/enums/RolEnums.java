package cu.edu.cujae.logs.core.enums;

public enum RolEnums {

    SUPERADMINISTRADO("Super Administrador"),
    ADMINISTRADOR("Administrador"),
    GESTOR("Gestor");


    private String nombre;


    private RolEnums(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

}
