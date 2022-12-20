package cn.tpkf.bot;

import cn.tpkf.bot.utils.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Harlan
 */
@SpringBootApplication
public class PiBotApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PiBotApplication.class, args);
        SpringUtils.setAppContext(context);
    }

}
