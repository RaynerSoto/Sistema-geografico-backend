package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.clases.Sexo;
import cu.edu.cujae.logs.core.enums.SexoEnums;
import cu.edu.cujae.logs.core.repository.SexoRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.SexoServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SexoService implements SexoServiceInterfaces {

    @Autowired
    private SexoRepository sexoRepository;

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
}
