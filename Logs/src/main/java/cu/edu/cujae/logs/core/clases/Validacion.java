package cu.edu.cujae.logs.core.clases;

import cu.edu.cujae.logs.core.enums.Sexo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class Validacion<E> {
    public static Validator validador(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator;
    }

    public static String comprobacionValidador(Validator validator, Object elemento){
        String resultado = "";
        if (validador().validate(elemento).isEmpty() == false){
            for (ConstraintViolation con : validator.validate(elemento)){
                resultado = con.getMessage();
                break;
            }
        }
        return resultado;
    }
}
