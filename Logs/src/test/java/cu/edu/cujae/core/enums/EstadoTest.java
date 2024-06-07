package cu.edu.cujae.core.enums;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EstadoTest {
    Estado estado = Estado.NO_AUTORIZADO;


    @Test
    public void testEstado() {
        System.out.println(estado.getValueConvert());
    }

}