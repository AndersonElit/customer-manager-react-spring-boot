package customer.manager.pgs.customer.db.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("customer")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    @Id
    @Column("id")
    private Long id;
    @Column("first_name")
    private String firstName;
    @Column("last_name")
    private String lastName;
}
