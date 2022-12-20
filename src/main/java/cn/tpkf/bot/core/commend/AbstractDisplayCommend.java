package cn.tpkf.bot.core.commend;

import cn.tpkf.bot.core.devices.i2c.display.oled.OledDisplayDevice;
import lombok.AllArgsConstructor;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/20
 */
@AllArgsConstructor
public abstract class AbstractDisplayCommend implements Commend {

    protected OledDisplayDevice oledDisplayDevice;
}
