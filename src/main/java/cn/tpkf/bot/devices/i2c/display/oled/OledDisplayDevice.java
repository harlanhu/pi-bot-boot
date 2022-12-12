package cn.tpkf.bot.devices.i2c.display.oled;

import cn.tpkf.bot.devices.SingleDevice;
import cn.tpkf.bot.devices.i2c.I2cDevice;
import cn.tpkf.bot.devices.i2c.display.DisplayDevice;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 11 下午 04:35
 */
public interface OledDisplayDevice extends DisplayDevice, SingleDevice, I2cDevice {

    /**
     * 初始化oled
     */
    void init();

    /**
     * 写入指令
     *
     * @param command 指令
     */
    void writeCommand(byte command);

}
