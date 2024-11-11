package customer.manager.rest.api.controller.handler;

import customer.manager.application.request.CustomerRequest;
import customer.manager.application.usecases.CustomerUseCase;
import customer.manager.domain.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerHandler {

    private final CustomerUseCase customerUseCase;

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(CustomerRequest.class)
                .flatMap(customerUseCase::save)
                .flatMap(customerResponse -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(customerResponse));
    }

}
