package pl.edu.wat.ai.app.interfaces.jms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JmsProducer {

    private final JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    void send(JmsMessage message){
        log.debug("send message "+message);
        jmsTemplate.convertAndSend(destinationQueue, message);
    }
}
