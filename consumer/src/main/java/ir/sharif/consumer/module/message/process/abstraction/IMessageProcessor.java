package ir.sharif.consumer.module.message.process.abstraction;

import ir.sharif.consumer.module.message.process.dto.ProcessMessageRequestDto;

public interface IMessageProcessor {
    void process(ProcessMessageRequestDto message);
}
