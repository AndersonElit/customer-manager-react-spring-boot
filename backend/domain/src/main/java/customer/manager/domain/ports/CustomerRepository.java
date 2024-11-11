package customer.manager.domain.ports;

import customer.manager.domain.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerRepository {
    Mono<Customer> save(Customer customer);
}
