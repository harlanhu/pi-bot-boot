package cn.tpkf.bot.config;

import cn.tpkf.bot.core.DeviceManager;
import cn.tpkf.bot.devices.digital.output.Buzzer;
import cn.tpkf.bot.devices.i2c.adda.Pcf8591;
import cn.tpkf.bot.devices.i2c.display.oled.Oled12864;
import cn.tpkf.bot.enums.PinEnums;
import com.pi4j.context.Context;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 10:46
 */
@Configuration
public class DeviceInitializer {

    @Bean
    public DeviceManager deviceManager(Executor asyncExecutor, Context pi4jContext) {
        Buzzer buzzer = new Buzzer(pi4jContext, PinEnums.D4, "buzzer");
        Pcf8591 pcf8591 = new Pcf8591(pi4jContext, "PCF8591");
        Oled12864 oled12864 = new Oled12864(pi4jContext, "oled12864");
        return new DeviceManager(asyncExecutor, buzzer, pcf8591, oled12864);
    }
}
