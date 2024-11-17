package customer.manager.application.usecases;

import customer.manager.application.mapper.Mapper;
import customer.manager.domain.ports.out.KafkaProducerRepository;
import customer.manager.domain.request.CustomerRequest;
import customer.manager.domain.response.CustomerResponse;
import customer.manager.domain.ports.in.CustomerPort;
import customer.manager.domain.model.Customer;
import customer.manager.domain.ports.out.CustomerRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@RequiredArgsConstructor
public class CustomerUseCase implements CustomerPort {

    private final CustomerRepository customerRepository;
    private final KafkaProducerRepository auditProducerRepository;
    private static final Logger logger = Logger.getLogger(CustomerUseCase.class.getName());

    @Override
    public Mono<CustomerResponse> save(CustomerRequest customerRequest) {
        logger.info("Saving customer: " + customerRequest.toString());
        return customerRepository.save(Mapper.map(customerRequest, Customer.class))
                .flatMap(savedCustomer -> auditProducerRepository.send("audit-topic", savedCustomer.getFirstName())
                        .map(message -> {
                            System.out.println(message);
                            return Mapper.map(savedCustomer, CustomerResponse.class);
                        }));
    }

}
