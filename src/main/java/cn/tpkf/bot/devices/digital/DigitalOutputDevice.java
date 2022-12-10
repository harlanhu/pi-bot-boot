package cn.tpkf.bot.devices.digital;

import cn.tpkf.bot.devices.Device;
import cn.tpkf.bot.devices.SingleDevice;
import com.pi4j.io.gpio.digital.DigitalOutput;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 06:15
 */
public interface DigitalOutputDevice extends Device, SingleDevice {

    /**
     * 获取 DigitalOutput
     * @return DigitalOutput
     */
    DigitalOutput getDigitalOutput();

    /**
     * 设置高电平
     */
    void on();

    /**
     * 设置高电平
     * @param duration 持续时间 毫秒
     */
    void on(long duration);

    /**
     * 设置低电平
     */
    void off();

    /**
     * 反转电平
     * @return true 高电平, false 低电平
     */
    boolean toggle();

    /**
     * 设置电平
     * @param state true 高电平, false 低电平
     */
    void setState(boolean state);

}
