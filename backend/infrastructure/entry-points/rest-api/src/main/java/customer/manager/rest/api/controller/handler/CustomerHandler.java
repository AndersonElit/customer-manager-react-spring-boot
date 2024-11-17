package customer.manager.rest.api.controller.handler;

import customer.manager.domain.request.CustomerRequest;
import customer.manager.domain.ports.in.CustomerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CustomerHandler {

    private final CustomerPort customerPort;

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(CustomerRequest.class)
                .flatMap(customerPort::save)
                .flatMap(customerResponse -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(customerResponse));
    }

}
