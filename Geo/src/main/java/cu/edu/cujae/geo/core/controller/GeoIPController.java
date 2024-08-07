package cu.edu.cujae.geo.core.controller;

import cu.edu.cujae.geo.core.clases.PosicionIP;
import cu.edu.cujae.geo.core.interfacesServices.GeoIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/geoIp")
public class GeoIPController {

    private final GeoIP geoIP;

    @Autowired
    public GeoIPController(GeoIP geoIP) {
        this.geoIP = geoIP;
    }

    @GetMapping("/")
    private ResponseEntity<?> geoIP(PosicionIP posicionIP) {
        try {
            return ResponseEntity.ok(geoIP.findMyIp(posicionIP.getIp()));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
