package cu.edu.cujae.core.servicesImpl;

import cu.edu.cujae.core.services.GeoIPService;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PosicionIPTest {

    @Test
    public void TestPosicionIP() {
        assertNull(new GeoIPService().findMyIp("192.168.137.1",new GeoIPService().getKeyPropierties()).continent_code());
    }

    @Test
    public void TestPosicionIP2() {
        assertNotNull(new GeoIPService().findMyIp("152.206.189.86", new GeoIPService().getKeyPropierties()).continent_code());
    }

    @Test
    public void leer_archivo(){
        System.out.println(new GeoIPService().leer_archivo());
    }
}