package com.zma.highload.course.message.producer.app.client;

public interface QueueClient {
    void put();

    String getProducerName();
}
