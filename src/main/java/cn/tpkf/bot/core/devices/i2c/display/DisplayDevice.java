package cn.tpkf.bot.core.devices.i2c.display;

import cn.tpkf.bot.core.devices.i2c.display.oled.font.Rotation;
import cn.tpkf.bot.core.devices.SingleDevice;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 11 下午 12:19
 */
public interface DisplayDevice extends SingleDevice {

    /**
     * 获取显示高度
     *
     * @return 高度
     */
    int getDisplayHeight();


    /**
     * 获取显示宽度
     *
     * @return 宽度
     */
    int getDisplayWidth();

    /**
     * 获取最大像素点下标
     *
     * @return 下标
     */
    int getMaxIndex();

    /**
     * 获取显示角度
     *
     * @return 角度
     */
    Rotation getRotation();

    /**
     * 获取高度
     *
     * @return 实际高度
     */
    int getWidth();

    /**
     * 获取宽度
     *
     * @return 实际宽度
     */
    int getHeight();

    /**
     * 清空屏幕
     */
    void clearDisplay();
}
