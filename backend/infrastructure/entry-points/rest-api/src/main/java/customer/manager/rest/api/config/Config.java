package customer.manager.rest.api.config;

import customer.manager.application.usecases.CustomerUseCase;
import customer.manager.application.usecases.impl.CustomerUseCaseImpl;
import customer.manager.domain.ports.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public CustomerUseCase customerUseCase(CustomerRepository customerRepository) {
        return new CustomerUseCaseImpl(customerRepository);
    }

}
