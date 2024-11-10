package customer.manager.pgs.customer.db.repository;

import customer.manager.pgs.customer.db.entities.CustomerEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PgsCustomerRepository extends ReactiveCrudRepository<CustomerEntity, Long> {
}
