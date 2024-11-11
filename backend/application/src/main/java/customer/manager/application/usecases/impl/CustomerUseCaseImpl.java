package customer.manager.application.usecases.impl;

import customer.manager.application.usecases.CustomerUseCase;
import customer.manager.domain.model.Customer;
import customer.manager.domain.ports.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CustomerUseCaseImpl implements CustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public Mono<Customer> save(Customer customer) {
        return customerRepository.save(customer);
    }

}
