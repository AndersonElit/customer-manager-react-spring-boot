package customer.manager.application.usecases.impl;

import customer.manager.domain.request.CustomerRequest;
import customer.manager.domain.response.CustomerResponse;
import customer.manager.application.usecases.CustomerUseCase;
import customer.manager.domain.model.Customer;
import customer.manager.domain.ports.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CustomerUseCaseImpl implements CustomerUseCase {

    private final CustomerRepository customerRepository;

    @Override
    public Mono<CustomerResponse> save(CustomerRequest customerRequest) {
        return customerRepository.save(Customer.builder()
                        .firstName(customerRequest.getFirstName())
                        .lastName(customerRequest.getLastName())
                .build())
                .map(savedCustomer -> CustomerResponse.builder()
                        .id(savedCustomer.getId())
                        .firstName(savedCustomer.getFirstName())
                        .lastName(savedCustomer.getLastName())
                        .build());
    }

}
