package customer.manager.kafka.audit.producer.adapter;

import customer.manager.domain.ports.out.KafkaProducerRepository;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.util.HashMap;
import java.util.Map;

@Service
public class KafkaProducer implements KafkaProducerRepository {

    private final KafkaSender<String, String> sender;

    public KafkaProducer(@Value("${spring.kafka.bootstrap-servers}") String bootstrapServers) {

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.ACKS_CONFIG, "all");

        SenderOptions<String, String> senderOptions = SenderOptions.create(props);
        this.sender = KafkaSender.create(senderOptions);
    }

    @Override
    public Mono<String> send(String topic, String message) {
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
