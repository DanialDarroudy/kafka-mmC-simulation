package ir.sharif.consumer.Listener;

import ir.sharif.consumer.module.message.process.abstraction.IMessageProcessor;
import ir.sharif.consumer.module.message.process.dto.ProcessMessageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
@RequiredArgsConstructor
public class MessageListener {
    private final ExecutorService executor;
    private final IMessageProcessor processor;

    @KafkaListener(topics = "${kafka.topic.name}", concurrency = "1")
    public void listen(ProcessMessageRequestDto message, Acknowledgment ack) {
        executor.submit(() -> {
            try {
                processor.process(message);
                ack.acknowledge();
            } catch (Exception ignored) {}
        });
    }
}
