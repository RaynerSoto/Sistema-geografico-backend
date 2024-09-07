package cu.edu.cujae.logs.core.utils;

import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.dto.usuario.UsuarioDto;
import cu.edu.cujae.logs.core.mapper.Estado;
import cu.edu.cujae.logs.core.mapper.Registro;
import cu.edu.cujae.logs.core.services.EstadoServiceInterfaces;
import cu.edu.cujae.logs.core.services.RegistroServiceInterfaces;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroUtils {
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private RegistroServiceInterfaces registroService;
    @Autowired
    private EstadoServiceInterfaces estadoService;

    public RegistroDto registroHttpUtils(HttpServletRequest request,String actividad){
        UsuarioDto user = tokenUtils.userToken(request);
        RegistroDto registroDto = new RegistroDto();
        registroDto.setIp(IpUtils.hostIpV4Http(request));
        registroDto.setActividad(actividad);
        return registroDto;
    }

    public void insertarRegistros(RegistroDto registroDto,UsuarioDto usuarioDto,String estadoActual){
        Registro registro = null;
        Estado estado = estadoService.obtenerEstado(estadoActual).get();
        if (usuarioDto != null){
            registro = new Registro(registroDto,usuarioDto.getUuid(),estado);
        }else {
            registro = new Registro(registroDto,estado);
        }
        registroService.insertarRegistro(registro);
    }
}
