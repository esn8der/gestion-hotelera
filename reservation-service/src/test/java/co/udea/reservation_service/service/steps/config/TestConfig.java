package co.udea.reservation_service.service.steps.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@TestConfiguration
@ComponentScan(basePackages = "co.udea.reservation_service", excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                DataSourceAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        })
})
public class TestConfig {

}
