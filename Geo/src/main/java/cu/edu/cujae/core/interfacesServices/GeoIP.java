package cu.edu.cujae.core.interfacesServices;

import cu.edu.cujae.core.dto.CoordenadasIp;

public interface GeoIP {
    public CoordenadasIp findMyIp(String ip,String key);
}
