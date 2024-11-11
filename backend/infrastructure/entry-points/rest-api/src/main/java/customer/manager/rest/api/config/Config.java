package customer.manager.rest.api.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "customer.manager.application.usecases",
        includeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.REGEX,
                        pattern = ".*UseCaseImpl?$"
                )
        },
        useDefaultFilters = false
)
public class Config {
}
