package cu.edu.cujae.logs.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegioRolDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String rol;

    private String codigo;

}
