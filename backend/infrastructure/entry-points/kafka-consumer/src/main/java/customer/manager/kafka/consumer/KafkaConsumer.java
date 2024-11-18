package customer.manager.kafka.consumer;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import java.util.Collections;
import java.util.logging.Logger;

public abstract class KafkaConsumer {
    private final ReceiverOptions<String, String> receiverOptions;
    private final Logger logger;

    protected KafkaConsumer(ReceiverOptions<String, String> receiverOptions) {
        this.receiverOptions = receiverOptions;
        this.logger = Logger.getLogger(this.getClass().getName());
    }

    @EventListener(ApplicationStartedEvent.class)
    public Flux<Void> consume() {
        logger.info("Starting Kafka consumer for topic: " + getTopicName());

        return KafkaReceiver.create(receiverOptions.subscription(Collections.singleton(getTopicName())))
                .receive()
                .map(ConsumerRecord::value)
                .flatMap(this::processMessage);
    }

    protected abstract String getTopicName();
    protected abstract Mono<Void> processMessage(String message);
}
