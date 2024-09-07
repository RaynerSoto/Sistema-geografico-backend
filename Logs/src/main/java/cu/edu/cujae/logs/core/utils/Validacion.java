package cu.edu.cujae.logs.core.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class Validacion {

    //Validador de elementos
    public static String comprobacionValidador(Object elemento){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        if (validator.validate(elemento).isEmpty() == false){
            for (ConstraintViolation con : validator.validate(elemento)){
                return con.getMessage();
            }
        }
        return "";
    }

    public static void validarUnsupportedOperationException(Object elemento){
        String validacion = Validacion.comprobacionValidador(elemento);
        if (validacion.isBlank() == false)
            throw new UnsupportedOperationException(validacion);
    }
}
