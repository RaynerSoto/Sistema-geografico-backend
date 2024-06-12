package cu.edu.cujae.logs;
import cu.edu.cujae.logs.core.servicesInterfaces.EstadoServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.PrivilegioServiceInterfaces;
import cu.edu.cujae.logs.core.servicesInterfaces.SexoServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogsApplication implements CommandLineRunner {
    @Autowired
    private SexoServiceInterfaces sexoService;
    @Autowired
    private EstadoServiceInterfaces estadoService;
    @Autowired
    private PrivilegioServiceInterfaces privilegioService;

    public static void main(String[] args) {
        SpringApplication.run(LogsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        sexoService.iniciar();
        estadoService.iniciar();
        privilegioService.iniciar();
    }
}
