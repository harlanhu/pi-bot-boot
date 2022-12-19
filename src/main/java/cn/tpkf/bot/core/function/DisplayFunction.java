package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.devices.i2c.adda.Pcf8591;
import cn.tpkf.bot.core.devices.i2c.display.oled.Oled12864;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 18 下午 07:01
 */
public class DisplayFunction extends AbstractFunction {

    private final Oled12864 oled12864;

    private final Pcf8591 pcf8591;

    public DisplayFunction(String name, Oled12864 oled12864, Pcf8591 pcf8591) {
        super(name);
        this.oled12864 = oled12864;
        this.pcf8591 = pcf8591;
    }

    @Override
    protected void doSetUp() {

    }

    @Override
    protected void doStart() {

    }

    @Override
    protected void doStop() {

    }
}
