package customer.manager.application.usecases;

import customer.manager.application.mapper.Mapper;
import customer.manager.domain.ports.out.AuditProducerRepository;
import customer.manager.domain.request.CustomerRequest;
import customer.manager.domain.response.CustomerResponse;
import customer.manager.domain.ports.in.CustomerPort;
import customer.manager.domain.model.Customer;
import customer.manager.domain.ports.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CustomerUseCase implements CustomerPort {

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
