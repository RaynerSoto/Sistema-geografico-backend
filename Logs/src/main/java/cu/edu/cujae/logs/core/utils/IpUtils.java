package cu.edu.cujae.logs.core.utils;

import jakarta.servlet.http.HttpServletRequest;

public class IpUtils {
    public static String hostIpV4Http(HttpServletRequest request) {
        return request.getRemoteHost();
    }
}
