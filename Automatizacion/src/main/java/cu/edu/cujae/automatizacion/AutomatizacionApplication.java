package cu.edu.cujae.automatizacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AutomatizacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomatizacionApplication.class, args);
    }

}
