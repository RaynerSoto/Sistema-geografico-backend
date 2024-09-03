package cu.edu.cujae.gestion.core.servicesIntern;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServicesIntern {
	// Crear libro sin importar la situación
	public Workbook construccion_libro(Object object) throws Exception {
		try {
			if (object instanceof String) {
				return creacion_libro((String) object);
			} else if (object instanceof File) {
				return creacion_libro((File) object);
			} else {
				throw new IllegalArgumentException("El tipo de objeto no es compatible para la creación de un Workbook.");
			}
		} catch (Exception e) {
			throw new Exception("No se puede convertir el fichero al formato especificado: " + e.getMessage(), e);
		}
	}

	// Crear el libro de Excel a partir de la dirección
	private Workbook creacion_libro(String direccion) throws IOException, InvalidFormatException {
		File file = new File(direccion);
		if (!file.exists()) {
			throw new IOException("El archivo no existe en la ruta especificada: " + direccion);
		}
		return new XSSFWorkbook(direccion);
	}

	// Crear libro a partir del fichero
	private Workbook creacion_libro(File file) throws IOException, InvalidFormatException {
		if (!file.exists()) {
			throw new IOException("El archivo no existe: " + file.getAbsolutePath());
		}
		return new XSSFWorkbook(file);
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
