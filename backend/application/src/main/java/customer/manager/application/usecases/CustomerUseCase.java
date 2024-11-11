package customer.manager.application.usecases;

import customer.manager.domain.request.CustomerRequest;
import customer.manager.domain.response.CustomerResponse;
import reactor.core.publisher.Mono;

public interface CustomerUseCase {
    Mono<CustomerResponse> save(CustomerRequest customerRequest);
}
