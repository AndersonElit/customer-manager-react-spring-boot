package customer.manager.kafka.consumer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Component
@Getter
@Setter
public class KafkaProperties {
    private String bootstrapServers;
    private String groupId;
    private String autoOffsetReset;
    private boolean enableAutoCommit;
}
