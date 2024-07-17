package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.repository.PrivilegioRolRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.PrivilegioRolServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegioRolService implements PrivilegioRolServiceInterfaces {

    @Autowired
    private PrivilegioRolRepository privilegioRolRepository;

    @Override
    public void addPrivilegioRol(PrivilegioRol privilegioRol) throws Exception {
        privilegioRolRepository.save(privilegioRol);
    }

    @Override
    public void eliminarPrivilegioRol(PrivilegioRol privilegioRol) {
        privilegioRolRepository.delete(privilegioRol);
    }
}
