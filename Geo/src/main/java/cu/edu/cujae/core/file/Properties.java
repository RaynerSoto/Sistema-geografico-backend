package cu.edu.cujae.core.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Data
@AllArgsConstructor
public class Properties {
    private static final String aplication = "src/main/resources/application.properties";

    public String getAplication(){
        return aplication;
    }

    //Cargar la llave de las propiedadades
    public String getKeyPropierties(String url,String key){
        try {
            java.util.Properties properties = new java.util.Properties();
            File file = new File(url);
            FileReader fileReader = new FileReader(file);
            properties.load(fileReader);
            return properties.getProperty(key);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo no encontrado");
        } catch (IOException e) {
            throw new RuntimeException("Archivo no encontrado, corrupto o no se puede leer");
        }
    }
}
