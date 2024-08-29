package cu.edu.cujae.gestion.core.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServicesIntern {
	//Crear libro sin importar la situaciòn
	public Workbook construccion_libro(Object object) throws EncryptedDocumentException, IOException {
		return object instanceof String ? creacion_libro((String)object):creacion_libro((File)object);
	}
	
	
	//Crear el libro de Excel a partir de la direcciòn
	private Workbook creacion_libro(String direccion) throws EncryptedDocumentException, IOException {
		return WorkbookFactory.create(new File(direccion));
	}
	
	//Crear libro a partir del fichero
	private Workbook creacion_libro(File file) throws EncryptedDocumentException, IOException {
		return WorkbookFactory.create(file);
	}
	
	
	//Listado hojas de un libro
	public ArrayList<Sheet> listado_hojas(Workbook libro){
		ArrayList<Sheet>hojas = new ArrayList<Sheet>();
		for(int contador = 0; contador<libro.getNumberOfSheets();contador++) {
			hojas.add((Sheet) libro.getSheetAt(contador));
		}
		return hojas;
	}

	//Convertir de MultipartFile a File
	public File convertMultipartFileToFile(MultipartFile multipartFile) throws Exception {
		try{
			// Crear un archivo temporal con el mismo nombre que el archivo original
			File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
			// Transferir el contenido del MultipartFile al archivo temporal
			multipartFile.transferTo(convFile);
			return convFile;
		}catch (Exception e){
			throw new Exception("No se ha podido procesar el fichero ");
		}
	}
}
