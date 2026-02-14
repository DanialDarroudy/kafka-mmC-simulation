package ir.sharif.consumer.module.message.process.business;

import ir.sharif.consumer.module.message.process.abstraction.IMessageProcessor;
import ir.sharif.consumer.module.message.process.dto.ProcessMessageRequestDto;
import ir.sharif.consumer.module.metric.service.abstraction.IMetricService;
import ir.sharif.consumer.module.metric.service.dto.MetricServiceRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProcessor implements IMessageProcessor {
    private final IMetricService metricService;

    @Value("${simulation.service-mean-ms}")
    private long meanServiceTime;

    @Override
    public void process(ProcessMessageRequestDto message) {
        var serverId = Thread.currentThread().getName();
        var dequeueTime = System.currentTimeMillis();
        var waitingTime = dequeueTime - message.getArrivalTime();
        var serviceTime = generateExponential(meanServiceTime);
        var busyStart = System.currentTimeMillis();

        try {
            Thread.sleep(serviceTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        var finishTime = System.currentTimeMillis();
        var responseTime = finishTime - message.getArrivalTime();
        var busyDuration = finishTime - busyStart;

        var metricRecord = new MetricServiceRecord(serverId, waitingTime, responseTime, serviceTime, busyDuration);
        metricService.record(metricRecord);
    }

    private long generateExponential(long mean) {
        return (long) (-mean * Math.log(1 - Math.random()));
    }
}
