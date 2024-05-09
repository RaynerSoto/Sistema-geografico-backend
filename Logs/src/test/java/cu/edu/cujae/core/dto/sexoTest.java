package cu.edu.cujae.core.dto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

public class sexoTest {
    @Test
    public void crear_sexo(){
        Sexo sex = new Sexo("WER","Masculino",'M');
        System.out.println(sex.getNombre());
    }
}