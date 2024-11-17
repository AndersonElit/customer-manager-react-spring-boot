package customer.manager.domain.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerRequest {
    private String firstName;
    private String lastName;
}
