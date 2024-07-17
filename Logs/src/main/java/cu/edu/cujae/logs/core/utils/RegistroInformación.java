package cu.edu.cujae.logs.core.utils;

import com.google.gson.Gson;
import cu.edu.cujae.logs.core.dto.RegistroDto;
import cu.edu.cujae.logs.core.mapping.Registro;
import cu.edu.cujae.logs.core.record.CoordenadasIp;
import cu.edu.cujae.logs.core.servicesInterfaces.RegistroServiceInterfaces;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class RegistroInformación {
    @Autowired
    private RegistroServiceInterfaces registroService;

    public void insertaRegistro(RegistroDto registroDto) throws Exception {
        registroService.insertarRegistro(null);
    }
}
