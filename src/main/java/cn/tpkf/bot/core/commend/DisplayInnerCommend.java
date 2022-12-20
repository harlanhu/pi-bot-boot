package cn.tpkf.bot.core.commend;

import cn.tpkf.bot.core.devices.i2c.display.oled.OledDisplayDevice;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/20
 */
public class DisplayInnerCommend extends AbstractDisplayCommend {

    public DisplayInnerCommend(String name, OledDisplayDevice oledDisplayDevice) {
        super(name, oledDisplayDevice);
    }

    @Override
    public void execute() {

    }
}
