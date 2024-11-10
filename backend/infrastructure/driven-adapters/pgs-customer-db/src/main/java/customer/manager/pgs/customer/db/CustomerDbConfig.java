package customer.manager.pgs.customer.db;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "customer.manager.pgs.customer.db.repository")
@ComponentScan(basePackages = "customer.manager.pgs.customer.db")
public class CustomerDbConfig {
}
