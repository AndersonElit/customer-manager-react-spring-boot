package customer.manager.kafka.audit.producer.adapter;

import customer.manager.domain.ports.out.KafkaProducerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.util.logging.Logger;

@Service
public class KafkaProducer implements KafkaProducerRepository {

    private final KafkaSender<String, String> sender;
    private static final Logger logger = Logger.getLogger(KafkaProducer.class.getName());

    public KafkaProducer(KafkaSender<String, String> sender) {
        this.sender = sender;
    }

    @Override
    public Mono<String> send(String topic, String message) {
        logger.info("Sending message: " + message);
        return sender.send(Mono.just(SenderRecord.create(topic, null, null, null, message, null)))
                .next()
                .map(result -> String.format(
                            "Message sent successfully to topic %s, partition %d, offset %d",
                            result.recordMetadata().topic(),
                            result.recordMetadata().partition(),
                            result.recordMetadata().offset()
                    ));
    }
}
