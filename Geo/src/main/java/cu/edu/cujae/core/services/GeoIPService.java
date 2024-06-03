package cu.edu.cujae.core.services;

import com.google.gson.Gson;
import cu.edu.cujae.core.dto.CoordenadasIp;
import cu.edu.cujae.core.interfacesServices.GeoIP;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class GeoIPService implements GeoIP {
    @Override
    public CoordenadasIp findMyIp(String ip) {
        Gson gson = new Gson();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.ipgeolocation.io/ipgeo?apiKey=42d281b6590243ccb5932cdd2e8673f6&ip="+ip)).build();
        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            CoordenadasIp coordenadasIp = gson.fromJson((String) response.body(),CoordenadasIp.class);
            return coordenadasIp;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error en petición HTTP, compruebe conexión a internet o llamar al servicio técnico");
        }
    }
}
