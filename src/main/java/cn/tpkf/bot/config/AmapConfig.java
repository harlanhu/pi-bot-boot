package cn.tpkf.bot.config;

import cn.tpkf.bot.properties.AmapProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/19
 */
@Configuration
@EnableConfigurationProperties(AmapProperties.class)
public class AmapConfig {
}
