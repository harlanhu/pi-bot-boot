package cn.tpkf.bot.config;

import cn.tpkf.bot.properties.AliCloudProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/19
 */
@Configuration
@EnableConfigurationProperties(AliCloudProperties.class)
public class AliCloudConfig {
}
