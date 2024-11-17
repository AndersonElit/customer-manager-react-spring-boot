package customer.manager.domain.ports.out;

import reactor.core.publisher.Mono;

public interface AuditProducerRepository {
    Mono<String> send(String message);
}
