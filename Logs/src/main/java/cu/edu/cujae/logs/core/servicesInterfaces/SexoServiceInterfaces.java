package cu.edu.cujae.logs.core.servicesInterfaces;

import cu.edu.cujae.logs.core.mapping.Sexo;

import java.util.List;
import java.util.Optional;

public interface SexoServiceInterfaces {
    public void iniciar();

    public List<Sexo> listarSexos();

    public Optional<Sexo> consultarSexo(Long id);

    public Optional<Sexo> consultarSexo(String sexo);
}
