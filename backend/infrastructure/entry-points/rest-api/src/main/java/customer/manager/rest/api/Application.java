package customer.manager.rest.api;

import customer.manager.pgs.customer.db.CustomerDbConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(CustomerDbConfig.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
