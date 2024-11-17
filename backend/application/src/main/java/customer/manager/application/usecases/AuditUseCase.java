package customer.manager.application.usecases;

import customer.manager.domain.ports.in.AuditPort;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

public class AuditUseCase implements AuditPort {

    private static final Logger logger = Logger.getLogger(AuditUseCase.class.getName());

    @Override
    public Mono<Void> audit(String message) {
        logger.info("message consumed from usecase: " + message);
        return Mono.just( message).doOnNext(System.out::println)
                .then();
    }
}
