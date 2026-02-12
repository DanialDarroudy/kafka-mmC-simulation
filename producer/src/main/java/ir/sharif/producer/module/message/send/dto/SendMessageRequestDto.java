package ir.sharif.producer.module.message.send.dto;

import lombok.Data;

@Data
public class SendMessageRequestDto {
    private byte[] payload;
}
