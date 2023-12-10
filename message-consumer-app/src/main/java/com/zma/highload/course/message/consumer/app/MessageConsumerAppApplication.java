package com.zma.highload.course.message.consumer.app;

import com.surftools.BeanstalkClient.Client;
import com.surftools.BeanstalkClientImpl.ClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MessageConsumerAppApplication {
    @Value("${beanstalk_hostname:localhost}")
    private String beanstalkHostname;

    @Value("${beanstalk_port:11300}")
    private Integer beanstalkPort;

    @Bean
    public Client client( ) {
        return new ClientImpl(beanstalkHostname, beanstalkPort);
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageConsumerAppApplication.class, args);
    }
}
