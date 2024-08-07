package cu.edu.cujae.geo.core.services;

import com.google.gson.Gson;
import cu.edu.cujae.geo.core.dto.CoordenadasIp;
import cu.edu.cujae.geo.core.interfacesServices.GeoIP;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeoIPService implements GeoIP {

    @Override
    public CoordenadasIp findMyIp(String ip) {
        try {
            String url = System.getenv("IPKey");
            HttpRequest request = HttpRequest.newBuilder(URI.create(url+"&ip="+ip)).build();
            HttpResponse response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            CoordenadasIp coordenadasIp = new Gson().fromJson((String) response.body(),CoordenadasIp.class);
            return coordenadasIp;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en petición HTTP, compruebe conexión a internet o llamar al servicio técnico");
        }
    }
}
