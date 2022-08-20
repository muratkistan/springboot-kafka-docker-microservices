package com.muratkistan.db.consumer;


import com.muratkistan.db.model.Wikimedia;
import com.muratkistan.db.repository.WikimediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaDBConsumer {

    private final WikimediaRepository wikimediaRepository;



    @KafkaListener(topics = "wikimedia",groupId = "myGroup")
    public void consume(String message){
        log.info("Received message : "+ message);
        Wikimedia wikimedia =  Wikimedia.builder()
                .wikiData(message)
                .build();

        wikimediaRepository.save(wikimedia);



    }
}
