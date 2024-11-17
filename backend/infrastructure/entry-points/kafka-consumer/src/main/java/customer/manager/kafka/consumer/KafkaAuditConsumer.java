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

@Component
@RequiredArgsConstructor
public class KafkaAuditConsumer {

    private final Map<String, Object> kafkaConsumerProps;
    private final AuditPort auditPort;

    public Mono<Void> consume() {
        ReceiverOptions<String, String> receiverOptions = ReceiverOptions
                .<String, String>create(kafkaConsumerProps)
                .subscription(Collections.singleton("audit-topic"));

        KafkaReceiver<String, String> receiver = KafkaReceiver.create(receiverOptions);

        return receiver.receive()
                .map(ConsumerRecord::value)
                .last()
                .doOnNext(System.out::println)
                .flatMap(auditPort::audit);
    }
}
