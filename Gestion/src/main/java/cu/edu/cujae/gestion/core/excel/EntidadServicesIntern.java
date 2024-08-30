package cu.edu.cujae.gestion.core.excel;
import java.sql.Time;
import java.util.ArrayList;

import cu.edu.cujae.gestion.core.dto.EntidadDto;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

@Service
public class EntidadServicesIntern {
	//Cargar entidades en una lista
	public ArrayList<EntidadDto> extraer_entidades(Sheet hoja) {
		ArrayList<EntidadDto>listado_entidades = new ArrayList<>();
		for(int contador_fila = 1; contador_fila<hoja.getLastRowNum()+1;contador_fila++) {
			try {
				EntidadDto entidadDto = new EntidadDto();
				String nombre = null;
				String provincia = null;
				String municipio = null;
				String entidad = null;
				String direccion = null;
				String calle = null;
				String entrecalle1 = null;
				String entrecalle2 = null;
				String numero = null;
				String localidad = null;
				String datos = null;
				Time horario_actual_entrada = null;
				Time horario_actual_salida = null;
				Time horario_propuesto_entrada = null;
				Time horario_propuesto_salida = null;
				for(int contador_columna=0;contador_columna<hoja.getRow(0).getLastCellNum();contador_columna++) {
					if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("id.centro trabajo") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("centrotrabajo") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("centro")) {
						try {
							nombre = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							nombre = "";
						}finally {
							entidadDto.setNombre(nombre);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("nombreentidad") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("nombredeentidad") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("entidad") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("entidadsuperior") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("pertenecea") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("pertenecientea") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("pertenecientea:")) {
						try {
							entidad = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							entidad = "";
						}finally {
							entidadDto.setEntidadMadre(entidad);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("municipio") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("municipios")) {
						try {
							municipio = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							municipio = "";
						}finally {
							entidadDto.setMunicipio(municipio);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("provincia") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("provincias")) {
						try {
							provincia = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							provincia = "";
						}finally {
							entidadDto.setProvincia(provincia);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("direccion") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("direcci�n") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Direccion completa") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Direcci�n completa") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("direccionescompletas")) {
						try {
							direccion = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							direccion = "";
						}finally {
							entidadDto.setDireccion(direccion);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("numero") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("n�mero")) {
						try {
							numero = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (NullPointerException e2) {
							numero = "";
						}finally {
							entidadDto.setNumeroCasa(numero);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("localidad")) {
						try {
							localidad = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							localidad = "";
						}finally {
							entidadDto.setLocalidad(localidad);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("datos") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Datos adicionales") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Datosadicionales") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("datosadicionales") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("datos adicionales")) {
						try {
							datos = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							datos = "";
						}finally {
							entidadDto.setDatos(datos);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Horario actual de entrada")) {
						try {
							horario_actual_entrada = Time.valueOf(hoja.getRow(contador_fila).getCell(contador_columna).getLocalDateTimeCellValue().toLocalTime());
						} catch (Exception e2) {
							horario_actual_entrada = null;
						}finally {
							entidadDto.setHorario_entrada(horario_actual_entrada);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Horario actual de salida")) {
						try {
							horario_actual_salida = Time.valueOf(hoja.getRow(contador_fila).getCell(contador_columna).getLocalDateTimeCellValue().toLocalTime());
						} catch (Exception e2) {
							horario_actual_salida = null;
						}finally {
							entidadDto.setHorario_salida(horario_actual_salida);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Horario propuesto entrada")) {
						try {
							horario_propuesto_entrada = Time.valueOf(hoja.getRow(contador_fila).getCell(contador_columna).getLocalDateTimeCellValue().toLocalTime());
						} catch (Exception e2) {
							horario_propuesto_entrada = null;
						}finally {
							entidadDto.setHorario_propuesto_entrada(horario_propuesto_entrada);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Horario propuesto de salida")) {
						try {
							horario_propuesto_salida = Time.valueOf(hoja.getRow(contador_fila).getCell(contador_columna).getLocalDateTimeCellValue().toLocalTime());
						} catch (Exception e2) {
							horario_propuesto_salida = null;
						}finally {
							entidadDto.setHorario_propuesto_salida(horario_propuesto_salida);
						}
					}
				}
				listado_entidades.add(entidadDto);
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
		return listado_entidades;
	}
}
