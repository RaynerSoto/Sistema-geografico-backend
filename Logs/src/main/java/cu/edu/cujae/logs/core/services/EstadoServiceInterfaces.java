package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.mapper.Estado;

import java.util.List;
import java.util.Optional;

public interface EstadoServiceInterfaces {
    void iniciar();

    List<Estado> listarEstados();

    Optional<Estado> obtenerEstado(String nombre);
}
