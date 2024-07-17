package cu.edu.cujae.logs.core.services;

import cu.edu.cujae.logs.core.repository.PrivilegiosRepository;
import cu.edu.cujae.logs.core.servicesInterfaces.PrivilegioServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Privilegio> obtenerPrivilegio(String codigo) throws Exception {
        return Optional.ofNullable(privilegiosRepository.findByCodigoEqualsIgnoreCase(codigo).orElseThrow(
                () -> new Exception("No se encontró el privilegio por el código")));
    }
}
