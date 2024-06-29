package cu.edu.cujae.gestion.core.mapping;

import com.google.gson.annotations.Since;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.opengis.geometry.Geometry;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {

    @NotNull(message = "El id de provincia no puede ser nulo")
    private Long id;

    @NotNull(message = "El cuerpo geométrico no puede ser nulo")
    private Geometry geometry;

    @Size(min = 4,max = 100,message = "El nombre de la provincia  debe estar  entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @Size(min = 2,max = 100,message = "El código de la provincia  debe estar  entre 2 y 100 caracteres")
    @NotBlank(message = "El código no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "El código no puede ser nulo")
    private String codigo;

    @Min(value = 1,message = "El mínimo de población, debe ser 1")
    @NotNull(message = "La población no puede ser nula")
    private Long poblacion;

    @DecimalMin(value = "0.00000000000000000000001",message = "El valor mínimo debe ser 0.00000000000000000000001")
    @NotNull(message = "El valor del área en kilómetros cuadrados no debe ser nulo")
    private Double areakm2;

    @NotBlank(message = "Las siglas no puede estar vacío o estar compuesto solamente por espacios")
    @NotNull(message = "Las siglas no puede ser nulo")
    private String siglas;

    @NotNull(message = "El centroide no puede ser nulo")
    private Geometry centroide;
}
