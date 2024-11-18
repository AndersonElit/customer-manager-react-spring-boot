package customer.manager.mongo.repository;

import customer.manager.mongo.document.CustomerDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoCustomerRepository extends ReactiveMongoRepository<CustomerDocument, Long> {
}
