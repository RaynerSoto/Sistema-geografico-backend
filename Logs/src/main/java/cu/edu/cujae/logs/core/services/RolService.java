package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.clases.Rol;
import cu.edu.cujae.logs.core.repository.RolRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService implements RolServiceInterfaces {
    @Autowired
    private RolRepository rolRepository;

    public RolService(RolRepository rolRepository){
        this.rolRepository = rolRepository;
    }

    @Override
    public void insertarRol(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public List<Rol> consultarRol() {
        return rolRepository.findAll();
    }
}
