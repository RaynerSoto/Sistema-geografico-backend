package cu.edu.cujae.core.file;

import cu.edu.cujae.core.security.Base64Cifrado;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Data
@AllArgsConstructor
public class Dat {

    private static final String file = "config.dat";

    public String getFile(){
        return file;
    }

    public String leer_archivo(String fichero){
        try {
            File file = new File(fichero);
            if (!file.exists()){
                throw new Exception("Error al cargar fichero de configuración");
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            String value = br.readLine();
            fileReader.close();
            br.close();
            return new Base64Cifrado().descifrarBase64(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
