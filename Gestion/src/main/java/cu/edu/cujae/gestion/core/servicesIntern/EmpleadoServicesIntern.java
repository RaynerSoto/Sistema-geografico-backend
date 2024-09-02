package cu.edu.cujae.gestion.core.servicesIntern;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDto;
import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDtoInsert;
import cu.edu.cujae.gestion.core.dto.empleadoDtos.EmpleadoDtoRegular;
import cu.edu.cujae.gestion.core.feignclient.TokenServiceInterfaces;
import cu.edu.cujae.gestion.core.libs.RegistroUtils;
import cu.edu.cujae.gestion.core.libs.Validacion;
import cu.edu.cujae.gestion.core.mapping.Empleado;
import cu.edu.cujae.gestion.core.mapping.Entidad;
import cu.edu.cujae.gestion.core.mapping.Municipio;
import cu.edu.cujae.gestion.core.mapping.Provincia;
import cu.edu.cujae.gestion.core.services.RegistroService;
import cu.edu.cujae.gestion.core.servicesInterfaces.EmpleadoServiceInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.EntidadServicesInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.MunicipioServicesInterfaces;
import cu.edu.cujae.gestion.core.servicesInterfaces.ProvinciaServiceInterfaces;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServicesIntern {

	private final EmpleadoServiceInterfaces empleadoService;
	private final MunicipioServicesInterfaces municipioService;
	private final ProvinciaServiceInterfaces provinciaService;
	private final EntidadServicesInterfaces entidadService;
	private final RegistroService registroService;
	private final RegistroUtils registroUtils;
	private final TokenServiceInterfaces tokenService;

	@Autowired
	public EmpleadoServicesIntern(EmpleadoServiceInterfaces empleadoService, MunicipioServicesInterfaces municipioService, ProvinciaServiceInterfaces provinciaService, EntidadServicesInterfaces entidadService, RegistroService registroService, RegistroUtils registroUtils, TokenServiceInterfaces tokenService) {
		this.empleadoService = empleadoService;
		this.municipioService = municipioService;
		this.provinciaService = provinciaService;
		this.entidadService = entidadService;
		this.registroService = registroService;
		this.registroUtils = registroUtils;
		this.tokenService = tokenService;
	}

	//Cargar persona en una lista
	public ArrayList<EmpleadoDtoInsert> extraer_personas(Sheet hoja) {
		ArrayList<EmpleadoDtoInsert>listado_personas = new ArrayList<>();
		for(int contador_fila = 1; contador_fila<hoja.getLastRowNum()+1;contador_fila++) {
			try {
				EmpleadoDtoInsert empleadoDto = new EmpleadoDtoInsert();
				String entidad = null;
				String nombre = null;
				String ci = null;
				String provincia = null;
				String municipio = null;
				String direccion = null;
				String calle = null;
				String entrecalle1 = null;
				String entrecalle2 = null;
				String numero = null;
				String localidad = null;
				String datos = null;
				for(int contador_columna=0;contador_columna<hoja.getRow(0).getLastCellNum();contador_columna++) {
					if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("nombre") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("nombreyapellidos") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("nombres") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Nombres y apellidos")) {
						try {
							nombre = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							nombre = "";
						}finally {
							empleadoDto.setNombre(nombre);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("nombreentidad") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("nombredeentidad") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("entidad") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("entidadsuperior") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("pertenecea") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("pertenecientea") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("pertenecientea:") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Id.Sede")) {
						try {
							entidad = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							entidad = "";
						}finally {
							empleadoDto.setEntidad(entidad);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("municipio") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("municipios")) {
						try {
							municipio = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							municipio = "";
						}finally {
							empleadoDto.setMunicipio(municipio);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("provincia") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("provincias")) {
						try {
							provincia = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							provincia = "";
						}finally {
							empleadoDto.setProvincia(provincia);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("direccion") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("direcci�n") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Direccion completa") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Direcci�n completa") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("direccionescompletas")) {
						try {
							direccion = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							direccion = "";
						}finally {
							empleadoDto.setDireccion(direccion);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("numero") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("n�mero")) {
						try {
							numero = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							numero = "";
						}finally {
							empleadoDto.setNumero(numero);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("localidad")) {
						try {
							localidad = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							localidad = "";
						}finally {
							empleadoDto.setLocalidad(localidad);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("datos") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("Datos adicionales") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("datosadicionales")) {
						try {
							datos = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							datos = "";
						}finally {
							empleadoDto.setDatos(datos);
						}
					}
					else if(hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("ci") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("carnet") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("carnet de identidad") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("carnetdeidentidad") || hoja.getRow(0).getCell(contador_columna).getStringCellValue().trim().equalsIgnoreCase("CI")) {
						try {
							ci = hoja.getRow(contador_fila).getCell(contador_columna).getStringCellValue();
						} catch (Exception e2) {
							ci = "";
						} finally {
							empleadoDto.setCi(ci);
						}
					}
				}
				listado_personas.add(empleadoDto);
			}catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
		return listado_personas;
	}

	public void insertarEmpleadoSinTrabajo(EmpleadoDto empleadoDto) throws Exception{
		Validacion.validarUnsupportedOperationException(empleadoDto);
		Optional<Provincia> provincia = provinciaService.buscarProvinciaPorNombre(empleadoDto.getProvincia());
		Optional<Municipio> municipio = municipioService.obtenerMunicipioNombre(empleadoDto.getMunicipio());
		if (!municipioService.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
			throw new Exception("Este municipio no pertenece a la provincia");
		empleadoService.insertarEmpleado(new Empleado(empleadoDto,municipio.get(),provincia.get()));
	}

	public void insertarEmpleadoConTrabajo(EmpleadoDtoInsert empleadoDto) throws Exception{
		Validacion.validarUnsupportedOperationException(empleadoDto);
		Optional<Entidad> entidad = entidadService.obtenerEntidadNombre(empleadoDto.getEntidad());
		Optional<Provincia> provincia = provinciaService.buscarProvinciaPorNombre(empleadoDto.getProvincia());
		Optional<Municipio> municipio = municipioService.obtenerMunicipioNombre(empleadoDto.getMunicipio());
		if (!municipioService.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
			throw new Exception("Este municipio no pertenece a la provincia");
		entidad.get().getPersonal().add(new Empleado(empleadoDto,municipio.get(),provincia.get()));
		entidadService.modificarEntidad(entidad.get());
	}

	public List<EmpleadoDtoRegular> listadoEmpleadoDtoRegular() throws Exception{
		return empleadoService.obtenerEmpleados().stream()
				.map(empleado -> new EmpleadoDtoRegular(empleado)).toList();
	}

	public void modificarEmpleadoXId(EmpleadoDto empleadoDto,Long id) throws Exception{
		Validacion.validarUnsupportedOperationException(empleadoDto);
		empleadoDto.setUuid(id);
		Optional<Provincia> provincia = provinciaService.buscarProvinciaPorNombre(empleadoDto.getProvincia());
		Optional<Municipio> municipio = municipioService.obtenerMunicipioNombre(empleadoDto.getMunicipio());
		if (!municipioService.isMuncipioinProvincia(provincia.get().getNombre(),municipio.get().getNombre()))
			throw new Exception("Este municipio no pertenece a la provincia");
		empleadoService.modificarEmpleado(new Empleado(empleadoDto,municipio.get(),provincia.get()));
	}
}
