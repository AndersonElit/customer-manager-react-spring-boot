package customer.manager.domain.ports.in;

import customer.manager.domain.request.CustomerRequest;
import customer.manager.domain.response.CustomerResponse;
import reactor.core.publisher.Mono;

public interface CustomerPort {
    Mono<CustomerResponse> save(CustomerRequest customerRequest);
}
