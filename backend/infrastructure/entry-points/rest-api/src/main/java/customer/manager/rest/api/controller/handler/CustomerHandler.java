package customer.manager.rest.api.controller.handler;

import customer.manager.pgs.customer.db.Customer;
import customer.manager.pgs.customer.db.adapter.CustomerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CustomerHandler {

    private final CustomerAdapter customerAdapter;

    @Autowired
    public CustomerHandler(CustomerAdapter customerAdapter) {
        this.customerAdapter = customerAdapter;
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(Customer.class)
                .flatMap(customerAdapter::save)
                .flatMap(savedCustomer -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(savedCustomer));
    }

}
