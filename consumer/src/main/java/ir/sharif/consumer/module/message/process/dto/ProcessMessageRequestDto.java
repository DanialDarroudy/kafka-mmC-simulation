package ir.sharif.consumer.module.message.process.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessMessageRequestDto {
    private UUID id;
    private String payload;
    private Instant arrivalTime;
}
