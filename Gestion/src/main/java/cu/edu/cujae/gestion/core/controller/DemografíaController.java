package cu.edu.cujae.gestion.core.controller;

import cu.edu.cujae.gestion.core.dto.DemografiaDto;
import cu.edu.cujae.gestion.core.mapper.Empleado;
import cu.edu.cujae.gestion.core.mapper.Entidad;
import cu.edu.cujae.gestion.core.mapper.Municipio;
import cu.edu.cujae.gestion.core.mapper.Provincia;
import cu.edu.cujae.gestion.core.services.EmpleadoServiceInterfaces;
import cu.edu.cujae.gestion.core.services.EntidadServicesInterfaces;
import cu.edu.cujae.gestion.core.services.MunicipioServicesInterfaces;
import cu.edu.cujae.gestion.core.services.ProvinciaServiceInterfaces;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gestion/demografia")
public class DemografíaController {
    private final EntidadServicesInterfaces entidadServices;
    private final EmpleadoServiceInterfaces empleadoServices;
    private final ProvinciaServiceInterfaces provinciaServices;
    private final MunicipioServicesInterfaces municipioServices;

    @Autowired
    public DemografíaController(EntidadServicesInterfaces entidadServices, EmpleadoServiceInterfaces empleadoServices, ProvinciaServiceInterfaces provinciaServices, MunicipioServicesInterfaces municipioServices) {
        this.entidadServices = entidadServices;
        this.empleadoServices = empleadoServices;
        this.provinciaServices = provinciaServices;
        this.municipioServices = municipioServices;
    }

    @GetMapping("/interProvincial")
    @Operation(summary = "Movimientos demográficos interprovinciales",
            description = "Permite obtener todo el listado de los movimientos demográficos interprovinciales",
            security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAnyRole('Super Administrador','Administrador','Gestor')")
    public ResponseEntity<?> movilidadPoblacionalInterProvincial(){
       try{
           List<Provincia> provinciaList = provinciaServices.listadoProvincia();
           List<DemografiaDto> listado = new ArrayList<>();
           for (Provincia provincia : provinciaList) {
               for (Provincia provincia1 : provinciaList) {
                   DemografiaDto demografiaDto = new DemografiaDto();
                   demografiaDto.setOrigen(provincia.getNombre());
                   demografiaDto.setDestino(provincia1.getNombre());
                   for (Entidad entidad : entidadServices.listarEntidad()){
                       if(entidad.getProvincia().getNombre().equalsIgnoreCase(demografiaDto.getDestino())){
                           for (Empleado empleado : entidad.getPersonal()){
                               if (empleado.getProvincia().getNombre().equalsIgnoreCase(demografiaDto.getOrigen())){
                                   demografiaDto.setCantidad(demografiaDto.getCantidad()+1);
                               }
                           }
                       }
                   }
                   listado.add(demografiaDto);
               }
           }
           return ResponseEntity.ok(listado);
       } catch (Exception e) {
           throw new RuntimeException("No se ha podido obtener el reporte demográfico, compruebe su conexiòn a la base de datos o contacto con el servicio tècnico");
       }
    }

    @GetMapping("/intermunicipal/{provincia}")
    @Operation(summary = "Movimientos demográficos intermunicipales dentro de una provincia",
            description = "Permite obtener todo el listado de los movimientos demográficos interprovinciales",
            security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasAnyRole('Super Administrador','Administrador','Gestor')")
    public ResponseEntity<?> movilidadPoblacionalInterMunicipal(@PathVariable String provincia){
        try{
            Provincia prov = provinciaServices.buscarProvinciaPorNombre(provincia).get();
            List<DemografiaDto> listado = new ArrayList<>();
            for(Municipio municipio : prov.getListadoMunicipios()){
                for(Municipio municipio1: prov.getListadoMunicipios()){
                    DemografiaDto demografiaDto = new DemografiaDto();
                    demografiaDto.setOrigen(municipio.getNombre());
                    demografiaDto.setDestino(municipio1.getNombre());
                    for(Entidad entidad: municipio1.getEntidades()){
                        for (Empleado empleado : entidad.getPersonal()){
                            if (empleado.getMunicipio().getNombre().equalsIgnoreCase(demografiaDto.getOrigen())){
                                demografiaDto.setCantidad(demografiaDto.getCantidad()+1);
                                if (demografiaDto.getCantidad() != 0){
                                    System.out.println(demografiaDto);
                                }
                            }
                        }
                    }
                    listado.add(demografiaDto);
                }
            }
            return ResponseEntity.ok(listado);
        } catch (Exception e) {
            throw new RuntimeException("No se ha podido obtener el reporte demográfico, compruebe su conexiòn a la base de datos o contacto con el servicio tècnico");
        }
    }
}
