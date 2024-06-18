package cu.edu.cujae.geo.core.interfacesServices;

import cu.edu.cujae.geo.core.dto.CoordenadasIp;

public interface GeoIP {
    public CoordenadasIp findMyIp(String ip);
}
