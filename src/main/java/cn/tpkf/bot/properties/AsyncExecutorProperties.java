package cn.tpkf.bot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/14
 */
@Data
@ConfigurationProperties(prefix = "async")
public class AsyncExecutorProperties {

    private int corePoolSize = 10;

    private int maxPoolSize = 80;

    private int queueCapacity = 1000;

    private int keepAlive = 10;

    private String namePrefix = "PI-BOT Async Executor";
}
