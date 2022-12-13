package cn.tpkf.bot.devices.pwm;

import cn.tpkf.bot.devices.Device;
import cn.tpkf.bot.devices.SingleDevice;
import com.pi4j.io.pwm.Pwm;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 06:21
 */
public interface PwmDevice extends SingleDevice {

    /**
     * 获取Pwm
     * @return pwm
     */
    Pwm getPwm();

    /**
     * 播放频率
     * @param frequency 频率
     */
    void on(int frequency);

    /**
     * 持续播放频率
     * @param frequency 频率
     * @param duration 时间 / ms
     */
    void on(int frequency, long duration);

    /**
     * 关闭
     */
    void off();
}
