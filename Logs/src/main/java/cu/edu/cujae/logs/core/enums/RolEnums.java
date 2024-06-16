package cu.edu.cujae.logs.core.enums;

import cu.edu.cujae.logs.core.mapping.Privilegio;
import java.util.List;

public enum RolEnums {

    SUPERADMINISTRADO("Super Administrador",List.of(
            new Privilegio(PrivilegioEnums.ROL),
            new Privilegio(PrivilegioEnums.ENTIDAD),
            new Privilegio(PrivilegioEnums.PERSONAL),
            new Privilegio(PrivilegioEnums.USUARIO)
            ,new Privilegio(PrivilegioEnums.REPORTES),
            new Privilegio(PrivilegioEnums.USUARIOADMINISTRADOR))),
    ADMINISTRADOR("Administrador",List.of(
            new Privilegio(PrivilegioEnums.ROL),
            new Privilegio(PrivilegioEnums.ENTIDAD),
            new Privilegio(PrivilegioEnums.PERSONAL),
            new Privilegio(PrivilegioEnums.USUARIO)
            ,new Privilegio(PrivilegioEnums.REPORTES)
    )),
    GESTOR("Gestor",List.of(
            new Privilegio(PrivilegioEnums.PERSONAL),
            new Privilegio(PrivilegioEnums.USUARIO),
            new Privilegio(PrivilegioEnums.REPORTES)
    ));


    private String nombre;

    private List<Privilegio> privilegioList;

    private RolEnums(String nombre, List<Privilegio> privilegioList) {
        this.nombre = nombre;
        this.privilegioList = privilegioList;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Privilegio> getPrivilegioList() {
        return privilegioList;
    }

}
