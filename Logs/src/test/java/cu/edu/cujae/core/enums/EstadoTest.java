package cu.edu.cujae.core.enums;

import cu.edu.cujae.logs.core.enums.Estado;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EstadoTest {
    Estado estado = Estado.NO_AUTORIZADO;


    @Test
    public void testEstado() {
        System.out.println(estado.getValueConvert());
    }

}