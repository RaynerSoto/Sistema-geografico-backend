package cu.edu.cujae.logs.core.mapping;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cu.edu.cujae.logs.core.dto.SexoDto;
import cu.edu.cujae.logs.core.enums.SexoEnums;
import cu.edu.cujae.logs.core.repository.SexoRepository;
import cu.edu.cujae.logs.core.services.SexoService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "sexos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sexo{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "sexoID",unique = true, nullable = false,updatable = false)
    private Long id;

    @NotNull(message = "El nombre del sexo no puede ser null")
    @Size(min = 4,max = 100,message = "El nombre del sexo debe estar entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre del sexo no puede estar vacío")
    @Column(name = "nombre",nullable = false, length = 50,unique = true)
    private String nombre;

    @NotNull(message = "La sigla no puede ser null")
    @NotBlank(message = "La sigla no puede estar vacío")
    @Size(min = 1,max = 5,message = "Las sigla solamente tienen entre 1 y 5 caracteres")
    @Column(name = "sigla",nullable = false, length = 5,unique = true)
    private String sigla;

    @OneToMany(mappedBy = "sexo",cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Usuario> usuarioList;


    public Sexo (SexoEnums sexoEnums){
        this.nombre = sexoEnums.getNombre();
        this.sigla = sexoEnums.getSigla();
    }

    public Sexo(SexoDto sexoDto){
        this.id = sexoDto.getId();
        this.nombre = sexoDto.getNombre();
        this.sigla = sexoDto.getSigla();
    }

    public Sexo(Optional<Sexo> sexo) {
        this.id = sexo.get().getId();
        this.nombre = sexo.get().nombre;
        this.sigla = sexo.get().sigla;
    }
}
