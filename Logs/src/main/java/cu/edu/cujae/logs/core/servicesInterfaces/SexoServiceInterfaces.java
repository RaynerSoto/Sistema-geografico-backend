package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.clases.Sexo;

import java.util.List;
import java.util.Optional;

public interface SexoServiceInterfaces {
    public void iniciar();

    public List<Sexo> listarSexos();

    public Optional<Sexo> buscarSexoPorId(Long id);
}
