package customer.manager.kafka.consumer;

import customer.manager.domain.ports.in.CustomerEventPort;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class KafkaEventConsumer {

    private final ReceiverOptions<String, String> receiverOptions;
    private final CustomerEventPort customerEventPort;
    private static final Logger logger = Logger.getLogger(KafkaEventConsumer.class.getName());

    @EventListener(ApplicationStartedEvent.class)
    public Flux<Void> consume() {
        logger.info("Starting Kafka consumer for mongo messages");

        return KafkaReceiver.create(receiverOptions.subscription(Collections.singleton("mongo-topic")))
                .receive()
                .map(ConsumerRecord::value)
                .flatMap(customerEventPort::consume);
    }
}
