package cu.edu.cujae.gestion.core.services;

import cu.edu.cujae.gestion.core.dto.RegistroDto;

public interface RegistroServiceInterface {
    public void insertarRegistro(RegistroDto registroDto, String username);
}
