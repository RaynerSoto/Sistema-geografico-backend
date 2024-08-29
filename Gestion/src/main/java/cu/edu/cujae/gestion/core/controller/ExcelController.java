package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.excel.FileServices;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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
                fichero.deleteOnExit();
            }catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
            return null;
        }
    }
}
