package cu.edu.cujae.logs.core.utils;

import jakarta.servlet.http.HttpServletRequest;

public class IpUtils {
    public static String hostIpV4Http(HttpServletRequest request) {
        String ip = request.getRemoteHost();
        if (ip.equals("0:0:0:0:0:0:0:1") || ip.equalsIgnoreCase("0:0:0:0:0:0:0:1"))
            return "127.0.0.1";
        return ip;
    }
}
