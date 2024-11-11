package customer.manager.application.usecases;

import customer.manager.domain.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerUseCase {
    Mono<Customer> save(Customer customer);
}
