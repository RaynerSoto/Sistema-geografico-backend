package cu.edu.cujae.core.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.AssertTrue;
import org.junit.Before;
import org.junit.Test;
import java.util.Set;
import org.junit.*;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.validation.Validator;

import static junit.framework.TestCase.fail;

@SpringBootTest
public class sexoTest {

    private jakarta.validation.Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void crear_sexo(){
        Sexo sex = new Sexo("ASD","Masculino","                  ");
        Set<ConstraintViolation<Sexo>> violations = validator.validate(sex);
        if (violations.size() == 0){
            System.out.println("Prueba con éxito");
        }else {
            fail("La prueba ha fallado en la creación del sexo");
        }
    }

    @Test
    public void crear_sexo2(){
        Sexo sex = new Sexo("ASD","Masculino","Masc");
        Set<ConstraintViolation<Sexo>> violations = validator.validate(sex);
        if (violations.size() == 0){
            System.out.println("Prueba con éxito");
        }else {
            fail("La prueba ha fallado en la creación del sexo");
        }
    }

    @Test
    public void crear_sexo3(){
        Sexo sex = new Sexo("ASD","Masculino","M");
        Set<ConstraintViolation<Sexo>> violations = validator.validate(sex);
        if (violations.size() == 0){
            System.out.println("Prueba con éxito");
        }else {
            fail("La prueba ha fallado en la creación del sexo");
        }
    }
}