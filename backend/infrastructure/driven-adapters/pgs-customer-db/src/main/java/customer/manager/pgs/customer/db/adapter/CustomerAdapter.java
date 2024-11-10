package customer.manager.pgs.customer.db.adapter;

import customer.manager.pgs.customer.db.Customer;
import customer.manager.pgs.customer.db.entities.CustomerEntity;
import customer.manager.pgs.customer.db.repository.PgsCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerAdapter {

    @Autowired
    private final PgsCustomerRepository customerRepository;

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
