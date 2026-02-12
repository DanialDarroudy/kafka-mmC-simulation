package ir.sharif.producer.module.message.send.abstraction;

import ir.sharif.producer.module.message.send.dto.SendMessageRequestDto;

public interface IMessageSender {
    void send(SendMessageRequestDto dto);
}
