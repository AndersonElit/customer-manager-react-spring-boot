package customer.manager.application.usecases;

import customer.manager.application.utils.ObjectStringConverter;
import customer.manager.domain.model.Customer;
import customer.manager.domain.ports.in.CustomerEventPort;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

public class CustomerEventUseCase implements CustomerEventPort {

    private static final Logger logger = Logger.getLogger(CustomerEventUseCase.class.getName());

    @Override
    public Mono<Void> consume(String message) {
        logger.info("message consumed from usecase: " + message);
        return Mono.just(message)
                .map(m -> ObjectStringConverter.toObject(m, Customer.class))
                .then();
    }
}
