package cu.edu.cujae.logs.core.enums;

public enum PrivilegioEnums {
    ROL("Privilegios de acceso a la información de los roles","10X"),
    USUARIO("Privilegios de acceso a la información de los usuarios","20X"),
    ENTIDAD("Privilegios de acceso a la inforamción de la entidad","30X"),
    PERSONAL("Privilegios de acceso a la información del personal","40X"),
    REPORTES("Privilegios de acceso a la información de los reportes","50X")
    ;


    private String descripcion;
    private String codigo;

    PrivilegioEnums(String descripcion, String codigo) {
        this.descripcion = descripcion;
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigo() {
        return codigo;
    }
}
