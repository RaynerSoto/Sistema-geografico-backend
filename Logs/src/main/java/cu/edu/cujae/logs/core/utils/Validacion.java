package cu.edu.cujae.logs.core.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

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
