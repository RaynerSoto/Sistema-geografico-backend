package cu.edu.cujae.logs.core.enums;

public enum PrivilegioEnums {
    ROL("Privilegios de acceso a la información de los roles","10X"),
    ROLCREATE("Privilegios para insertar rol","101"),
    ROLREAD("Privilegios para leer roles","102"),
    ROLUPDATE("Privilegios para actualizar roles","103"),
    ROLDELETE("Privilegios para eliminar rol","104"),
    USUARIO("Privilegios de acceso a la información de los usuarios","20X"),
    USUARIOCREATE("Privilegios para insertar usuario","201"),
    USUARIOREAD("Privilegios para leer usuario","202"),
    USUARIOUPDATE("Privilegios para actualizar usuario","203"),
    USUARIODELETE("Privilegios para eliminar usuario","204"),
    USUARIOADMINISTRADOR("Privilegios para la creación,modificación y eliminación de los administradores","205"),
    ENTIDAD("Privilegios de acceso a la inforamción de la entidad","30X"),
    ENTIDADCREATE("Privilegios para insertar entidad","301"),
    ENTIDADREAD("Privilegios para leer entidad","302"),
    ENTIDADUPDATE("Privilegios para actualizar entidad","303"),
    ENTIDADDELETE("Privilegios para eliminar entidad","304"),
    PERSONAL("Privilegios de acceso a la información del personal","40X"),
    PERSONALCREATE("Privilegios para insertar personal","401"),
    PERSONALREAD("Privilegios para leer personal","402"),
    PERSONALUPDATE("Privilegios para actualizar personal","403"),
    PERSONALDELETE("Privilegios para eliminar personal","404"),
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
