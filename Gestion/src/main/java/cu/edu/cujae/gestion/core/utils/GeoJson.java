package cu.edu.cujae.gestion.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

public class GeoJson {
    public static String convetirGeoJson(Object object) throws JsonProcessingException {
        try {
            Gson mapper = new Gson();
            String geoJson = mapper.toJson(object);
            return geoJson;
        }catch (Exception e){
            System.out.println(e);
            return "No se pudo convertir a GeoJson";
        }
    }

}
