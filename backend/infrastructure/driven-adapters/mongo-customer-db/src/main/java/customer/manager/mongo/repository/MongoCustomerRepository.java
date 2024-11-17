package customer.manager.mongo.repository;

import customer.manager.mongo.document.CustomerDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MongoCustomerRepository extends ReactiveMongoRepository<CustomerDocument, Long> {
}
