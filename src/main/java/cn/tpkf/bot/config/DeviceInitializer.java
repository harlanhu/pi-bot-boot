package cn.tpkf.bot.config;

import cn.tpkf.bot.devices.digital.Buzzer;
import cn.tpkf.bot.devices.i2c.Pcf8591;
import cn.tpkf.bot.enums.PinEnums;
import com.pi4j.context.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 10:46
 */
@Configuration
@RequiredArgsConstructor
public class DeviceInitializer {

    private final Context pi4jContext;

    @Bean
    public Buzzer buzzer() {
        return new Buzzer(pi4jContext, PinEnums.D4, "buzzer");
    }

    @Bean
    public Pcf8591 pcf8591() {
        return new Pcf8591(pi4jContext, "PCF8591", 1);
    }
}
