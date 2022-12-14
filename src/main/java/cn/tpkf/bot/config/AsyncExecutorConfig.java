package cn.tpkf.bot.config;

import cn.tpkf.bot.properties.AsyncExecutorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/14
 */
@Slf4j
@EnableAsync
@Configuration
@EnableConfigurationProperties(AsyncExecutorProperties.class)
public class AsyncExecutorConfig {

    @Bean
    public Executor asyncExecutor(AsyncExecutorProperties properties) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaxPoolSize(properties.getMaxPoolSize());
        executor.setQueueCapacity(properties.getQueueCapacity());
        executor.setThreadNamePrefix(properties.getNamePrefix());
        executor.setKeepAliveSeconds(properties.getKeepAlive());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
