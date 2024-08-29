package cu.edu.cujae.gestion.core.excel;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

@Service
public class ExcelServices {
    public boolean isEntidadSheet(Sheet hoja){
        if(hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("entidad") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("entidades") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("trabajos") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("Centros de trabajos") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("centrodetrabajo") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("unidades") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("centro") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("lugardetrabajo") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("lugaresdetrabajo") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("lugaresdetrabajos"))
            return true;
        return false;
    }

    public boolean isPersonalSheet(Sheet hoja){
        if(hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("personas") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("personal") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("trabajadores") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("trabajador") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("empleados") ||
                hoja.getSheetName().toLowerCase().trim().equalsIgnoreCase("persona"))
            return true;
        return false;
    }
}
