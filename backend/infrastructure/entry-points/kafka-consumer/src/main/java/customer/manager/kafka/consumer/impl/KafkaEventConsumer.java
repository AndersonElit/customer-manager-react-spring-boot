package customer.manager.kafka.consumer.impl;

import customer.manager.domain.ports.in.CustomerEventPort;
import customer.manager.kafka.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.ReceiverOptions;

@Component
public class KafkaEventConsumer extends KafkaConsumer {

    private final CustomerEventPort customerEventPort;

    public KafkaEventConsumer(ReceiverOptions<String, String> receiverOptions, CustomerEventPort customerEventPort) {
        super(receiverOptions);
        this.customerEventPort = customerEventPort;
    }

    @Override
    protected String getTopicName() {
        return "mongo-topic";
    }

    @Override
    protected Mono<Void> processMessage(String message) {
        return customerEventPort.consume(message);
    }

}
