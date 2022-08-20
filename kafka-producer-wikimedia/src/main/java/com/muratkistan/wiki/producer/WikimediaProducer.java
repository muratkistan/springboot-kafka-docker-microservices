package com.muratkistan.wiki.producer;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import com.muratkistan.wiki.handler.WikimediaHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
@RequiredArgsConstructor
public class WikimediaProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    String topic;

    public void sendMessage() throws InterruptedException {



        EventHandler eventHandler = new WikimediaHandler(kafkaTemplate,topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource eventSource= builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(1000);


    }

}
