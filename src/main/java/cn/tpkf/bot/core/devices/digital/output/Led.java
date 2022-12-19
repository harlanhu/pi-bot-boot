package cn.tpkf.bot.core.devices.digital.output;

import cn.tpkf.bot.enums.PinEnums;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * LED
 *
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/14
 */
public class Led extends AbstractDoDevice {

    protected Led(Context pi4jContext, PinEnums pin, String name, DigitalState initial, DigitalState shutdown) {
        super(pi4jContext, pin, name, initial, shutdown);
    }

    @Override
    public void setUp() {
        loop();
    }
}
