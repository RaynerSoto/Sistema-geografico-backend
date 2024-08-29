package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.EntidadDto;
import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDto;
import cu.edu.cujae.gestion.core.excel.FileServices;
import cu.edu.cujae.gestion.core.mapping.Entidad;
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

    private final FileServices fileServices;

    @Autowired
    public ExcelController(FileServices fileServices) {
        this.fileServices = fileServices;
    }

    @PostMapping("/")
    public ResponseEntity<?> cargarUnExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Archivo vacío o no recibido");
        }else {
            try{
                File fichero = fileServices.convertMultipartFileToFile(file);
                Workbook libro = fileServices.construccion_libro(fichero);
                ArrayList<Sheet>hojas = fileServices.listado_hojas(libro);
                if (hojas.size() == 2) {
                    ArrayList<EntidadDto> entidades = new ArrayList<>();
                    ArrayList<EmpleadoDto> empleados = new ArrayList<>();
                    for(Sheet hoja : hojas){
                        int cantidad_filas = hoja.getLastRowNum()+1;
                        int cantida_columnas;
                        try {
                            cantida_columnas = hoja.getRow(0).getLastCellNum();
                        } catch (Exception e2) {
                            cantida_columnas = 0;
                        }
                        if(cantidad_filas != 0 && cantida_columnas != 0){

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
