package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.mapping.Rol;
import cu.edu.cujae.logs.core.enums.RolEnums;
import cu.edu.cujae.logs.core.repository.RolRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.RolServiceInterfaces;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class RolService implements RolServiceInterfaces {
    @Autowired
    private RolRepository rolRepository;

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
        if (rolRepository.existsById(id)){
            rolRepository.deleteById(id);
        }
        else {
            throw new Exception("El rol no existe");
        }
    }

    @Override
    public Optional<Rol> consultarRolID(Long id) throws Exception {
        return Optional.ofNullable(rolRepository.findById(id).orElseThrow(
                () -> new Exception("El rol con dicho id no existe")
        ));
    }

    @Override
    public Optional<Rol> consultarRol(String rol) throws Exception {
        return Optional.ofNullable(consultarRolNombre(rol).orElseThrow(
                () -> new RuntimeException("No se encontró el Rol")
        ));
    }

    @Override
    public Optional<Rol> consultarRolNombre(String rol) throws Exception {
        return rolRepository.findByRolEqualsIgnoreCase(rol);
    }


}
