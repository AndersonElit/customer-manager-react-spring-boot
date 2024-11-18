package customer.manager.application.usecases;

import customer.manager.application.utils.CustomerObjectsConverter;
import customer.manager.domain.ports.in.CustomerEventPort;
import customer.manager.domain.ports.out.CustomerEventRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class CustomerEventUseCase implements CustomerEventPort {

    private final CustomerEventRepository customerEventRepository;
    private static final Logger logger = Logger.getLogger(CustomerEventUseCase.class.getName());

    @Override
    public Mono<Void> consume(String message) {
        logger.info("message consumed from CustomerEventUseCase: " + message);
        return Mono.just(message)
                .map(CustomerObjectsConverter::convertStringToEvent)
                .map(CustomerObjectsConverter::getCustomerFromEventPayload)
                .flatMap(customerEventRepository::save)
                .then();
    }
}
