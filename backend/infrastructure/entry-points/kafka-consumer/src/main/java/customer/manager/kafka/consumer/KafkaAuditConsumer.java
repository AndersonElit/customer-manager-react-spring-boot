package customer.manager.kafka.consumer;

import customer.manager.domain.ports.in.AuditPort;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.receiver.KafkaReceiver;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class KafkaAuditConsumer {

    private final KafkaReceiver<String, String> receiver;
    private final AuditPort auditPort;
    private static final Logger logger = Logger.getLogger(KafkaAuditConsumer.class.getName());

    @EventListener(ApplicationStartedEvent.class)
    public void startConsumer() {
        logger.info("Starting Kafka consumer for audit messages");

        receiver.receive()
                .map(ConsumerRecord::value)
                .flatMap(auditPort::audit)
                .doOnNext(unused -> logger.info("Message processed successfully"))
                .doOnError(error -> logger.log(Level.SEVERE, "Error processing message", error))
                .onErrorContinue((error, obj) -> logger.log(Level.SEVERE, "Continuing after error", error))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }
}
