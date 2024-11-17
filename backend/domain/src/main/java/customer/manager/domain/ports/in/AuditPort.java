package customer.manager.domain.ports.in;

import reactor.core.publisher.Mono;

public interface AuditPort {
    Mono<Void> audit(String message);
}
