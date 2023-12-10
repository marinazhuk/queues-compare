package com.zma.highload.course.message.producer.app.client;

import com.surftools.BeanstalkClient.Client;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class BeanstalkdClient implements QueueClient {
    private static final String QUEUE_NAME = "queue";
    private static final byte[] VALUE_IN_BYTES = "value".getBytes(StandardCharsets.UTF_8);

    private final Client client;
    public BeanstalkdClient(Client client) {
        this.client = client;
        this.client.useTube(QUEUE_NAME);
    }

    @Override
    public String getProducerName() {
        return "Beanstalkd";
    }

    @Override
    public void put() {
        client.put(65536, 0, 120, VALUE_IN_BYTES);
    }
}
