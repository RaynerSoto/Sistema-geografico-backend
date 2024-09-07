package cu.edu.cujae.logs.core.services.servicesimpl;
import cu.edu.cujae.logs.core.mapper.Estado;
import cu.edu.cujae.logs.core.utils.enums.EstadoEnums;
import cu.edu.cujae.logs.core.repository.EstadoRepository;
import cu.edu.cujae.logs.core.services.EstadoServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService implements EstadoServiceInterfaces {

    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Override
    public void iniciar() {
        for (EstadoEnums estadoEnums : EstadoEnums.values()) {
            try{
                estadoRepository.save(new Estado(estadoEnums));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<Estado> listarEstados() {
        return estadoRepository.findAll();
    }

    @Override
    public Optional<Estado> obtenerEstado(String nombre) {
        return Optional.ofNullable(estadoRepository.findByNombreEqualsIgnoreCase(nombre).orElseThrow(
                () -> new RuntimeException("No existe el estado con el nombre: " + nombre)
        ));
    }
}
