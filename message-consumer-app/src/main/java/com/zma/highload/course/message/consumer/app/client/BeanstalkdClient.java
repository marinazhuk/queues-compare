package com.zma.highload.course.message.consumer.app.client;

import com.surftools.BeanstalkClient.Client;
import com.surftools.BeanstalkClient.Job;
import org.springframework.stereotype.Service;

@Service
public class BeanstalkdClient implements QueueClient {
    private static final String QUEUE_NAME = "queue";

    private final Client client;

    public BeanstalkdClient(Client client) {
        this.client = client;
        this.client.watch(QUEUE_NAME);
    }

    @Override
    public String getName() {
        return "Beanstalkd";
    }

    @Override
    public void get() {
        Job job = client.reserve(0);
        client.delete(job.getJobId());
    }
}
