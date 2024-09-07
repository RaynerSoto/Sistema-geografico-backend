package cu.edu.cujae.gestion.core.services.servicesImpl;

import cu.edu.cujae.gestion.core.exception.SearchException;
import cu.edu.cujae.gestion.core.mapper.Entidad;
import cu.edu.cujae.gestion.core.repository.EntidadRepository;
import cu.edu.cujae.gestion.core.services.EntidadServicesInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntidadService implements EntidadServicesInterfaces {

    private final EntidadRepository entidadRepository;

    @Autowired
    public EntidadService(EntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    @Override
    public void insertarEntidad(Entidad entidad) {
        entidadRepository.save(entidad);
    }

    @Override
    public void modificarEntidad(Entidad entidad) throws Exception{
        if (entidadRepository.existsById(entidad.getUuid()))
            entidadRepository.save(entidad);
        else{
            throw new Exception("La entidad ha modificar no existe");
        }
    }

    public void eliminarEntidad(Long id) throws Exception{
        if (entidadRepository.existsById(id))
            entidadRepository.deleteById(id);
        else {
            throw new Exception("No se ha encontrado la entidad a eliminar");
        }
    }

    @Override
    public List<Entidad> listarEntidad() {
        return entidadRepository.findAll();
    }

    @Override
    public void existeEntidadNombre(String nombre) throws Exception{
        if (entidadRepository.existsByNombreEqualsIgnoreCase(nombre))
            throw new Exception("Existe una entidad con dicho nombre ya insertada");
    }

    @Override
    public void existeEntidadNombreNotId(String nombre, Long id) throws Exception {
        if (entidadRepository.existsByNombreEqualsIgnoreCaseAndAndUuidNot(nombre, id))
            throw new SearchException("Existe una entidad que tiene ese nombre ya previamente insertada");
    }

    @Override
    public Optional<Entidad> obtenerEntidadNombre(String nombre) throws Exception {
        return Optional.ofNullable(entidadRepository.findByNombreEqualsIgnoreCase(nombre).orElseThrow(
                () -> new SearchException("No existe una entidad con el nombre " + nombre)
        ));
    }

    @Override
    public Optional<Entidad> obtenerEntidadID(Long id) throws Exception {
        return Optional.ofNullable(entidadRepository.findById(id).orElseThrow(
                () -> new SearchException("No existe una entidad con el id " + id)
        ));
    }
}
