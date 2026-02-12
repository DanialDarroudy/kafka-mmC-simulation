package ir.sharif.consumer.core.pool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class PoolDependencyInstaller {

    @Value("${simulation.server-count}")
    private int serverCount;

    @Bean
    public ExecutorService serverExecutor() {
        return new ThreadPoolExecutor(
                serverCount,
                serverCount,
                0L,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
