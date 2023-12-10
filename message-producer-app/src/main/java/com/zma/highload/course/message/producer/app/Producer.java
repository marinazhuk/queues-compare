package com.zma.highload.course.message.producer.app;

import com.zma.highload.course.message.producer.app.client.QueueClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final QueueClient client;

    public Producer(/*@Qualifier("redisClient")*/
                     @Qualifier("beanstalkdClient") //todo uncomment to change consumer
                            QueueClient client) {
        this.client = client;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void produce() {
        int count = 50000;

        long start = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            client.put();
        }

        long end = System.currentTimeMillis() - start;

        logger.info("{} puts were completed in {} ms by {}", count, end, client.getProducerName());

    }
}
