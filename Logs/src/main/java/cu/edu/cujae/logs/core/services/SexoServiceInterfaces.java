package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.mapper.Sexo;

import java.util.List;
import java.util.Optional;

public interface SexoServiceInterfaces {
    void iniciar();

    List<Sexo> listarSexos();

    Optional<Sexo> consultarSexo(Long id);

    Optional<Sexo> consultarSexo(String sexo);
}
