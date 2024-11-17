package customer.manager.domain.ports.out;

import reactor.core.publisher.Mono;

public interface KafkaProducerRepository {
    Mono<String> send(String topic, String message);
}
