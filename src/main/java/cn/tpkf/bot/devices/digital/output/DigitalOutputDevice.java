package cn.tpkf.bot.devices.digital.output;

import cn.tpkf.bot.devices.Device;
import cn.tpkf.bot.devices.SingleDevice;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;

import java.util.concurrent.TimeUnit;

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

    /**
     * 获取当前状态
     *
     * @return 当前状态
     */
    DigitalState getState();

    /**
     * 设置电平
     */
    void on();

    /**
     * 设置电平
     *
     * @param duration 持续时间
     * @param timeUnit 单位
     */
    void on(long duration, TimeUnit timeUnit);

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
     * @param timeUnit 单位
     */
    void loop(long duration, int loop, TimeUnit timeUnit);

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
     * @param timeUnit 单位
     */
    void cycle(long duration, int loop, long interval, int cycle, TimeUnit timeUnit);


}
