package customer.manager.application.events;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEvent {
    private String eventId;
    private String eventType;
    private Timestamp timestamp;
    private String payload;
}
