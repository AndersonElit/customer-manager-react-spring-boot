package customer.manager.kafka.consumer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka.consumer")
@Getter
@Setter
public class KafkaProperties {
    private String bootstrapServers;
    private String groupId;
    private String autoOffsetReset = "latest";
    private boolean enableAutoCommit = true;
}
