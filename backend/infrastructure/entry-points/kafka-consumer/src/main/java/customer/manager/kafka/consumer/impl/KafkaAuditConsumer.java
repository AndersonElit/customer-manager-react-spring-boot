package customer.manager.kafka.consumer.impl;

import customer.manager.domain.ports.in.AuditPort;
import customer.manager.kafka.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverOptions;

@Component
public class KafkaAuditConsumer extends KafkaConsumer {

    private final AuditPort auditPort;

    public KafkaAuditConsumer(ReceiverOptions<String, String> receiverOptions, AuditPort auditPort) {
        super(receiverOptions);
        this.auditPort = auditPort;
    }

    @Override
    protected String getTopicName() {
        return "audit-topic";
    }

    @Override
    protected Mono<Void> processMessage(String message) {
        return auditPort.audit(message);
    }

}
