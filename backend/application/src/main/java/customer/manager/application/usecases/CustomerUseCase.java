package customer.manager.application.usecases;

import customer.manager.application.request.CustomerRequest;
import customer.manager.application.response.CustomerResponse;
import customer.manager.domain.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerUseCase {
    Mono<CustomerResponse> save(CustomerRequest customerRequest);
}
