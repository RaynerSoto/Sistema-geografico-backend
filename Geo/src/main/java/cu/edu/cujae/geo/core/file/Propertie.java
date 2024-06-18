package cu.edu.cujae.geo.core.file;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Data
@AllArgsConstructor
public class Propertie {
    private static final String aplication = "src/main/resources/application.properties";

    public String getAplication(){
        return aplication;
    }

    //Cargar la llave de las propiedadades
    public String getKeyPropierties(String key){
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(new File("src/main/resources/application.properties")));
            return properties.getProperty(key);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Archivo no encontrado");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Archivo no encontrado, corrupto o no se puede leer");
        }
    }


    public Double getKeyPropiertiesDouble(String key){
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(new File("src/main/resources/application.properties")));
            return Double.parseDouble(properties.getProperty(key));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo no encontrado");
        } catch (IOException e) {
            throw new RuntimeException("Archivo no encontrado, corrupto o no se puede leer");
        }
    }

    public Integer getKeyPropiertiesInteger(String key){
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(new File("src/main/resources/application.properties")));
            return Integer.parseInt(properties.getProperty(key));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo no encontrado");
        } catch (IOException e) {
            throw new RuntimeException("Archivo no encontrado, corrupto o no se puede leer");
        }
    }
}
