package customer.manager.pgs.customer.db.adapter;

import customer.manager.domain.model.Customer;
import customer.manager.domain.ports.CustomerRepository;
import customer.manager.pgs.customer.db.entities.CustomerEntity;
import customer.manager.pgs.customer.db.repository.PgsCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerAdapter implements CustomerRepository {

    private final PgsCustomerRepository customerRepository;

    @Override
    public Mono<Customer> save(Customer customer) {
        return customerRepository.save(CustomerEntity.builder()
                        .id(customer.getId())
                        .firstName(customer.getFirstName())
                        .lastName(customer.getLastName())
                        .build())
                .map(customerEntity ->
                        Customer.builder()
                                .id(customerEntity.getId())
                                .firstName(customerEntity.getFirstName())
                                .lastName(customerEntity.getLastName())
                                .build()
                );
    }

}
