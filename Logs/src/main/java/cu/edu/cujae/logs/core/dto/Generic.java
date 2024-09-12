package cu.edu.cujae.logs.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Generic<A,B,C,D,E,F> {
    private A generico1 = null;
    private B generico2 = null;
    private C generico3 = null;
    private D generico4 = null;
    private E generico5 = null;
    private F generico6 = null;

    public Generic(Object... object) throws IllegalAccessException {
        Class<?> clase = this.getClass();
        for (int contador = 0; contador<clase.getDeclaredFields().length && contador <object.length; contador++) {
            Field field = clase.getDeclaredFields()[contador];
            field.setAccessible(true);
            field.set(this,object[contador]);
        }
    }
}
