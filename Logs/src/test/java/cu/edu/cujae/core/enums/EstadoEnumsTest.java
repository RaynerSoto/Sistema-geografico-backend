package cu.edu.cujae.core.enums;

import cu.edu.cujae.logs.core.enums.EstadoEnums;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EstadoEnumsTest {
    EstadoEnums estadoEnums = EstadoEnums.NO_AUTORIZADO;


    @Test
    public void testEstado() {
        System.out.println(estadoEnums.getValueConvert());
    }

}