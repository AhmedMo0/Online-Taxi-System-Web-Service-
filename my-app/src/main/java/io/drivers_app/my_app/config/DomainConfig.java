package io.drivers_app.my_app.config;

import java.beans.JavaBean;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.drivers_app.my_app.domain")
@EnableJpaRepositories("io.drivers_app.my_app.repos")
//@ComponentScan(basePackages = "io.drivers_app.my_app.operations")
@EnableTransactionManagement
public class DomainConfig {
}
