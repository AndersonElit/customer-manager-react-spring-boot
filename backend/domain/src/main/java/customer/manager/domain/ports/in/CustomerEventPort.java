package customer.manager.domain.ports.in;

import reactor.core.publisher.Mono;

public interface CustomerEventPort {
    Mono<Void> consume(String message);
}
