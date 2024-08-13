package cu.edu.cujae.gateway.core.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {

    @NotBlank(message = "El token no puede ser nulo o encontrase vacío")
    private String Token;
}
