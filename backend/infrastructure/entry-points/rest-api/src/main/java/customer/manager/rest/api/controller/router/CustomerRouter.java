package customer.manager.rest.api.controller.router;

import customer.manager.rest.api.controller.handler.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CustomerRouter {

    @Bean
    public RouterFunction<ServerResponse> route(CustomerHandler customerHandler) {
        return RouterFunctions
                .route()
                .POST("/customer/save", customerHandler::save)
                .build();
    }

}
