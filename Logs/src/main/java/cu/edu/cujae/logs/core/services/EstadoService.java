package cu.edu.cujae.logs.core.services;
import cu.edu.cujae.logs.core.clases.Estado;
import cu.edu.cujae.logs.core.enums.EstadoEnums;
import cu.edu.cujae.logs.core.repository.EstadoRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.EstadoServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService implements EstadoServiceInterfaces {
    @Autowired
    private EstadoRepository estadoRepository;


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
}
