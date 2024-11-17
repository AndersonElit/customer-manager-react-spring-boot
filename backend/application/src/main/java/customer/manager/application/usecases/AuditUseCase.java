package customer.manager.application.usecases;

import customer.manager.domain.ports.in.AuditPort;
import reactor.core.publisher.Mono;

public class AuditUseCase implements AuditPort {

    @Override
    public Mono<Void> audit(String message) {
        return Mono.just("message consumed from usecase: " + message).doOnNext(System.out::println)
                .then();
    }
}
