package cu.edu.cujae.logs;
import cu.edu.cujae.logs.core.services.EstadoServiceInterfaces;
import cu.edu.cujae.logs.core.services.RolServiceInterfaces;
import cu.edu.cujae.logs.core.services.SexoServiceInterfaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class LogsApplication implements CommandLineRunner {
    @Autowired
    private SexoServiceInterfaces sexoService;
    @Autowired
    private EstadoServiceInterfaces estadoService;
    @Autowired
    private RolServiceInterfaces rolService;

    public static void main(String[] args) {
        SpringApplication.run(LogsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Comienza la aplicación");
    }
}
