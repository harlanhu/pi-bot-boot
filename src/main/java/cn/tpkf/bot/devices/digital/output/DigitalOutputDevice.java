package cn.tpkf.bot.devices.digital.output;

import cn.tpkf.bot.devices.Device;
import cn.tpkf.bot.devices.SingleDevice;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 06:15
 */
public interface DigitalOutputDevice extends Device, SingleDevice {

    /**
     * 获取 DigitalOutput
     *
     * @return DigitalOutput
     */
    DigitalOutput getDigitalOutput();

    DigitalState getState();

    /**
     * 设置电平
     */
    void on();

    /**
     * 设置电平
     *
     * @param duration 持续时间 毫秒
     */
    void on(long duration);

    /**
     * 设置电平
     */
    void off();

    /**
     * 反转电平
     * @return true 高电平, false 低电平
     */
    boolean toggle();

    /**
     * 设置电平
     *
     * @param state true 高电平, false 低电平
     */
    void setState(DigitalState state);

    /**
     * 循环开关
     */
    void loop();

    /**
     * 循环开关
     *
     * @param duration 开关间隔
     * @param loop     开关次数
     */
    void loop(long duration, int loop);

    /**
     * 训话开关
     */
    void cycle();

    /**
     * 循环开关
     *
     * @param duration 开关间隔
     * @param loop     开关次数
     * @param interval 间隔时间
     * @param cycle    循环次数
     */
    void cycle(long duration, int loop, long interval, int cycle);


}
