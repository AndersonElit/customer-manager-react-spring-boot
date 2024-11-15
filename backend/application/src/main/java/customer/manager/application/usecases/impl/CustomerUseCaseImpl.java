package customer.manager.application.usecases.impl;

import customer.manager.application.mapper.Mapper;
import customer.manager.domain.ports.AuditProducerRepository;
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
    private final AuditProducerRepository auditProducerRepository;

    @Override
    public Mono<CustomerResponse> save(CustomerRequest customerRequest) {
        return customerRepository.save(Mapper.map(customerRequest, Customer.class))
                .flatMap(savedCustomer -> auditProducerRepository.send(savedCustomer.getFirstName())
                        .map(message -> {
                            System.out.println(message);
                            return Mapper.map(savedCustomer, CustomerResponse.class);
                        }));
    }

}
