package cu.edu.cujae.logs.core.services.servicesimpl;

import cu.edu.cujae.logs.core.mapper.Sexo;
import cu.edu.cujae.logs.core.utils.enums.SexoEnums;
import cu.edu.cujae.logs.core.repository.SexoRepository;
import cu.edu.cujae.logs.core.services.SexoServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SexoService implements SexoServiceInterfaces {

    private final SexoRepository sexoRepository;

    @Autowired
    public SexoService(SexoRepository sexoRepository) {
        this.sexoRepository = sexoRepository;
    }


    @Override
    public void iniciar() {
        for (SexoEnums sexoEnums : SexoEnums.values()) {
            try{
                sexoRepository.save(new Sexo(sexoEnums));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Sexo> listarSexos() {
        return sexoRepository.findAll();
    }

    @Override
    public Optional<Sexo> consultarSexo(Long id) {
        return sexoRepository.findById(id);
    }

    @Override
    public Optional<Sexo> consultarSexo(String sexo) {
        return Optional.ofNullable(buscarSexoPorNome(sexo).orElseThrow(()-> new RuntimeException("El sexo no se encontró")));
    }

    public Optional<Sexo> buscarSexoPorNome(String nombre) {
        return sexoRepository.findByNombreEqualsIgnoreCase(nombre);
    }
}
