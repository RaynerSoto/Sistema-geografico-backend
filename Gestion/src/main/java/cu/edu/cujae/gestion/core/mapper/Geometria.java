package cu.edu.cujae.gestion.core.mapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Geometry;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Geometria {

    @NotNull(message = "El cuerpo geométrico no puede ser nulo")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Geometry geometry;
    
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @NotNull(message = "El centroide no puede ser nulo")
    private Geometry centroide;
}
