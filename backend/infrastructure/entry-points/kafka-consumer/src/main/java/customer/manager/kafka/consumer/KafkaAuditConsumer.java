package customer.manager.kafka.consumer;

import customer.manager.domain.ports.in.AuditPort;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class KafkaAuditConsumer {

    private final Map<String, Object> kafkaConsumerProps;
    private final AuditPort auditPort;
    private static final Logger logger = Logger.getLogger(KafkaAuditConsumer.class.getName());

    public Mono<Void> consume() {
        logger.info("Consuming audit messages");
        ReceiverOptions<String, String> receiverOptions = ReceiverOptions
                .<String, String>create(kafkaConsumerProps)
                .subscription(Collections.singleton("audit-topic"));

        KafkaReceiver<String, String> receiver = KafkaReceiver.create(receiverOptions);

        return receiver.receive()
                .map(ConsumerRecord::value)
                .doOnNext(System.out::println)
                .last()
                .doOnNext(message -> logger.info("Consumed most recent audit message: " + message))
                .flatMap(auditPort::audit);
    }
}
