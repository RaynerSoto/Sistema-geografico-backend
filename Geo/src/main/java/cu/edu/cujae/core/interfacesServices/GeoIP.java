package cu.edu.cujae.core.interfaces;

import cu.edu.cujae.core.dto.CoordenadasIp;

public interface GeoIP {
    public CoordenadasIp findMyIp(String ip);
}
