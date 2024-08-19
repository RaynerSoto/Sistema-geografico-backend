package cu.edu.cujae.gestion.core.libs;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class FechaFormato {
    public static String formateoFecha(Timestamp fechaHora){
        if (fechaHora == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = sdf.format(new Date(fechaHora.getTime()));
        return fechaFormateada;
    }
}
