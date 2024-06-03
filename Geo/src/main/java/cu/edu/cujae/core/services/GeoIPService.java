package cu.edu.cujae.core.services;

import com.google.gson.Gson;
import cu.edu.cujae.core.dto.CoordenadasIp;
import cu.edu.cujae.core.interfacesServices.GeoIP;
import cu.edu.cujae.core.security.Base64Cifrado;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

@Service
public class GeoIPService implements GeoIP {
    @Override
    public CoordenadasIp findMyIp(String ip,String key) {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.ipgeolocation.io/ipgeo?apiKey="+key+"&ip="+ip)).build();
        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            CoordenadasIp coordenadasIp = gson.fromJson((String) response.body(),CoordenadasIp.class);
            return coordenadasIp;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en petición HTTP, compruebe conexión a internet o llamar al servicio técnico");
        }
    }
}
