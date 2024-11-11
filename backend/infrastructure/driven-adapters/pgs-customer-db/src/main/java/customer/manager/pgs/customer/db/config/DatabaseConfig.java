package customer.manager.pgs.customer.db.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "customer.manager.pgs.customer.db.repository")
public class DatabaseConfig {
}
