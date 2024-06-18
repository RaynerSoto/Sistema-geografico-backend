package cu.edu.cujae.core.servicesImpl;

import cu.edu.cujae.geo.core.file.Dat;
import cu.edu.cujae.geo.core.file.Propertie;
import cu.edu.cujae.geo.core.services.GeoIPService;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PosicionIPTest {

    @Test
    public void TestPosicionIP() {
        assertNull(new GeoIPService().findMyIp("192.168.137.1").continent_code());
    }

    @Test
    public void TestPosicionIP2() {
        assertNotNull(new GeoIPService().findMyIp("152.206.189.86").continent_code());
    }

    @Test
    public void leer_archivo(){
        System.out.println(new Dat().leer_archivo(new Dat().getFile()));
    }
}