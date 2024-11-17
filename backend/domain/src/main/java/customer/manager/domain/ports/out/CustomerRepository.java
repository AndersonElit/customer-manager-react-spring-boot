package customer.manager.domain.ports.out;

import customer.manager.domain.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerRepository {
    Mono<Customer> save(Customer customer);
}
