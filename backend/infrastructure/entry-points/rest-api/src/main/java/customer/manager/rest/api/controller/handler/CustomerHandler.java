package customer.manager.rest.api.controller.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CustomerHandler {

    public Mono<ServerResponse> hello() {
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just("Hola, como estas?"), String.class);
    }

}
