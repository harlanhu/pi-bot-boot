package cn.tpkf.bot.core.devices.digital.output;

import cn.tpkf.bot.enums.PinEnums;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalState;
import lombok.Getter;

/**
 * 蜂鸣器
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 11:11
 */
@Getter
public class Buzzer extends AbstractDoDevice {

    public Buzzer(Context pi4jContext, PinEnums pin, String name, DigitalState initial, DigitalState shutdown) {
        super(pi4jContext, pin, name, initial, shutdown, DigitalState.HIGH, DigitalState.LOW);
    }

    public Buzzer(Context pi4jContext, PinEnums pin, String name) {
        this(pi4jContext, pin, name, DigitalState.HIGH, DigitalState.LOW);
    }

    @Override
    public void setUp() {
        loop();
    }
}
