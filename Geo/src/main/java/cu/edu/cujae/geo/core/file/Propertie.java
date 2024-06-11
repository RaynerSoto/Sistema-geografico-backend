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
    public String getKeyPropierties(String url,String key){
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(new File(url)));
            return properties.getProperty(key);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo no encontrado");
        } catch (IOException e) {
            throw new RuntimeException("Archivo no encontrado, corrupto o no se puede leer");
        }
    }


    public Double getKeyPropiertiesDouble(String url,String key){
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(new File(url)));
            return Double.parseDouble(properties.getProperty(key));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo no encontrado");
        } catch (IOException e) {
            throw new RuntimeException("Archivo no encontrado, corrupto o no se puede leer");
        }
    }

    public Integer getKeyPropiertiesInteger(String url,String key){
        try {
            Properties properties = new Properties();
            properties.load(new FileReader(new File(url)));
            return Integer.parseInt(properties.getProperty(key));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo no encontrado");
        } catch (IOException e) {
            throw new RuntimeException("Archivo no encontrado, corrupto o no se puede leer");
        }
    }
}
