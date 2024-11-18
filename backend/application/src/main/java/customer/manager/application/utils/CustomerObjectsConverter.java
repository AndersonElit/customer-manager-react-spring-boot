package customer.manager.application.utils;

import customer.manager.application.events.CustomerEvent;
import customer.manager.domain.model.Customer;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerObjectsConverter {

    public static String convertEventToString(Customer customer, String eventType) {
        return ObjectStringConverter.toString(buildEvent(customer, eventType));
    }

    public static CustomerEvent convertStringToEvent(String message) {
        return ObjectStringConverter.toObject(message, CustomerEvent.class);
    }

    public static Customer getCustomerFromEventPayload(CustomerEvent customerEvent) {
        return ObjectStringConverter.toObject(customerEvent.getPayload(), Customer.class);
    }

    private static CustomerEvent buildEvent(Customer customer, String eventType) {
        return CustomerEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType(eventType)
                .timestamp(Timestamp.valueOf(LocalDateTime.now()))
                .payload(ObjectStringConverter.toString(customer))
                .build();
    }

}
