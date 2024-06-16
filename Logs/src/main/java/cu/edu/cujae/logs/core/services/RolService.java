package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.clases.Rol;
import cu.edu.cujae.logs.core.enums.RolEnums;
import cu.edu.cujae.logs.core.repository.RolRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RolService implements RolServiceInterfaces {
    @Autowired
    private RolRepository rolRepository;

    public RolService(RolRepository rolRepository){
        this.rolRepository = rolRepository;
    }

    @Override
    public void iniciar() {
        for (RolEnums rol : RolEnums.values()) {
            try {
                rolRepository.save(new Rol(rol));
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void insertarRol(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    public List<Rol> consultarRol() {
        return rolRepository.findAll();
    }


    public void modificarRol(Rol rol, Long id) throws Exception {
        if (rolRepository.existsById(id)){
            rol.setUuid(id);
            rolRepository.save(rol);
        }
        else {
            throw new Exception("El rol no existe");
        }

    }

    @Override
    public void eliminarRol(Long id) throws Exception {
        try{
            if (rolRepository.existsById(id)){
                rolRepository.deleteById(id);
            }
            else {
                throw new Exception("El rol no existe");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Optional<Rol> consultarRolID(Long id) throws Exception {
        return rolRepository.findById(id);
    }
}
