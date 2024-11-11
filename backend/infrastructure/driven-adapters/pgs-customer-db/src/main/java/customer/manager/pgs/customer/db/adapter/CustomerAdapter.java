package customer.manager.pgs.customer.db.adapter;

import customer.manager.application.mapper.Mapper;
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
        return customerRepository.save(Mapper.map(customer, CustomerEntity.class))
                .map(customerEntity -> Mapper.map(customerEntity, Customer.class));
    }

}
