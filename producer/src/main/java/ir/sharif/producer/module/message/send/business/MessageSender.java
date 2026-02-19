package ir.sharif.producer.module.message.send.business;

import ir.sharif.producer.module.message.common.model.MessageModel;
import ir.sharif.producer.module.message.send.abstraction.IMessageSender;
import ir.sharif.producer.module.message.send.dto.SendMessageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class MessageSender implements IMessageSender {
    private final KafkaTemplate<String, MessageModel> kafkaTemplate;
    @Value("${kafka.topic.name}")
    private String topicName;


    @Override
    public void send(SendMessageRequestDto dto) {
        var arrivalTime = Instant.now();
        var message = new MessageModel(dto.getPayload(), arrivalTime);
        kafkaTemplate.send(topicName, message.getId().toString(), message);
    }
}
