package customer.manager.mongo.adapter;

import customer.manager.application.mapper.Mapper;
import customer.manager.domain.model.Customer;
import customer.manager.domain.ports.out.CustomerEventRepository;
import customer.manager.mongo.document.CustomerDocument;
import customer.manager.mongo.repository.MongoCustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MongoCustomerAdapter implements CustomerEventRepository {

    private final MongoCustomerRepository mongoCustomerRepository;

    @Override
    public Mono<Customer> save(Customer customer) {
        return mongoCustomerRepository.save(Mapper.map(customer, CustomerDocument.class))
                .map(customerDocument -> Mapper.map(customerDocument, Customer.class));
    }

}
