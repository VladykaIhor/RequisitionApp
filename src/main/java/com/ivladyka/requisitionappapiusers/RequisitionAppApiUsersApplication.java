package com.ivladyka.requisitionappapiusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RequisitionAppApiUsersApplication {

    static final String topicExchangeName = "spring-boot-exchange";

    public static final String queueName = "spring-boot";

    public static void main(String[] args) {
        SpringApplication.run(RequisitionAppApiUsersApplication.class, args);
    }
}
