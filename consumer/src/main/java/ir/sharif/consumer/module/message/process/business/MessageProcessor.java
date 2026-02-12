package ir.sharif.consumer.module.message.process.business;

import ir.sharif.consumer.module.message.process.abstraction.IMessageProcessor;
import ir.sharif.consumer.module.message.process.dto.ProcessMessageRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProcessor implements IMessageProcessor {

    @Override
    public void process(ProcessMessageRequestDto message) {

    }
}
