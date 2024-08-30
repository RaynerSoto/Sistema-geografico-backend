package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.abstractas.General;
import cu.edu.cujae.gestion.core.servicesIntern.EntidadServicesIntern;
import cu.edu.cujae.gestion.core.servicesIntern.ExcelServicesIntern;
import cu.edu.cujae.gestion.core.servicesIntern.FileServicesIntern;
import cu.edu.cujae.gestion.core.servicesIntern.EmpleadoServicesIntern;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/gestion/excel")
@Tag(name = "Controlador del trabajo con el Excel"
        ,description = "Permite todo el trabajo relacionado con el Excel")
@SecurityRequirement(name = "bearer-key")
public class ExcelController {

    private final FileServicesIntern fileServicesIntern;
    private final ExcelServicesIntern excelServicesIntern;
    private final EntidadServicesIntern entidadServicesIntern;
    private final EmpleadoServicesIntern empleadoServicesIntern;

    @Autowired
    public ExcelController(FileServicesIntern fileServicesIntern, ExcelServicesIntern excelServicesIntern, EntidadServicesIntern entidadServicesIntern, EmpleadoServicesIntern empleadoServicesIntern) {
        this.fileServicesIntern = fileServicesIntern;
        this.excelServicesIntern = excelServicesIntern;
        this.entidadServicesIntern = entidadServicesIntern;
        this.empleadoServicesIntern = empleadoServicesIntern;
    }

    @PostMapping("/")

    public ResponseEntity<?> cargarUnExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Archivo vacío o no recibido");
        }else {
            try{
                File fichero = fileServicesIntern.convertMultipartFileToFile(file);
                Workbook libro = fileServicesIntern.construccion_libro(fichero);
                ArrayList<Sheet>hojas = fileServicesIntern.listado_hojas(libro);
                if (hojas.size() == 2) {
                    ArrayList<General> datos = new ArrayList<>();
                    for(Sheet hoja : hojas){
                        int cantidad_filas = hoja.getLastRowNum()+1;
                        int cantida_columnas;
                        try {
                            cantida_columnas = hoja.getRow(0).getLastCellNum();
                        } catch (Exception e2) {
                            cantida_columnas = 0;
                        }
                        if(cantidad_filas != 0 && cantida_columnas != 0){
                            if (excelServicesIntern.isEntidadSheet(hoja)){
                                datos.addAll(entidadServicesIntern.extraer_entidades(hoja));
                            }
                            else if(excelServicesIntern.isPersonalSheet(hoja)) {
                                datos.addAll(empleadoServicesIntern.extraer_personas(hoja));
                            }
                        }
                    }
                }
                else {
                    throw new Exception("Libro con una cantidad de hojas no computables");
                }
                fichero.deleteOnExit();
            }catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return null;
        }
    }
}
