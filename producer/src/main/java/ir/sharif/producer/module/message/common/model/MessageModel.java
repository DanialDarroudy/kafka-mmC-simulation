package ir.sharif.producer.module.message.common.model;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class MessageModel {
    private UUID id = UUID.randomUUID();
    private String payload;
    private Instant arrivalTime;

    public  MessageModel(String payload, Instant arrivalTime) {
        this.payload = payload;
        this.arrivalTime = arrivalTime;
    }
}
