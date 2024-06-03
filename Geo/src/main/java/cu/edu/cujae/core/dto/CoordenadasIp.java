package cu.edu.cujae.core.dto;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;

import java.awt.*;
public record CoordenadasIp(String continent_code,String continent_name,String country_name,
                            String country_name_official, String country_capital, String latitude, String longitude) {
}
