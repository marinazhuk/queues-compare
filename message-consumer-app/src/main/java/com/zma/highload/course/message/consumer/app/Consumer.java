package com.zma.highload.course.message.consumer.app;

import com.zma.highload.course.message.consumer.app.client.QueueClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final QueueClient client;

    public Consumer(/*@Qualifier("redisClient")*/
                     @Qualifier("beanstalkdClient") //todo uncomment to change consumer
                            QueueClient client) {
        this.client = client;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void consume() {
        int count = 50000;

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            client.get();
        }
        long end = System.currentTimeMillis() - start;

        logger.info("{} gets were completed in {} ms by {}", count, end, client.getName());
    }
}
