package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.mapping.Privilegio;
import cu.edu.cujae.logs.core.enums.PrivilegioEnums;
import cu.edu.cujae.logs.core.repository.PrivilegiosRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.PrivilegioServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegioService implements PrivilegioServiceInterfaces {
    @Autowired
    private PrivilegiosRepository privilegiosRepository;

    @Override
    public void iniciar() {
        for (PrivilegioEnums p : PrivilegioEnums.values()) {
            try {
                privilegiosRepository.save(new Privilegio(p));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<Privilegio> listarPrivilegios() {
        return privilegiosRepository.findAll();
    }
}
