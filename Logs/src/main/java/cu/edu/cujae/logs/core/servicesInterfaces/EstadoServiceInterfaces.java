package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.mapping.Estado;

import java.util.List;
import java.util.Optional;

public interface EstadoServiceInterfaces {
    public void iniciar();

    public List<Estado> listarEstados();

    public Optional<Estado> obtenerEstado(String nombre);
}
