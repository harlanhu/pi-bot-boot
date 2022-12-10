package cn.tpkf.bot.devices.pwm;

import cn.tpkf.bot.devices.pwm.AbstractPwmDevice;
import cn.tpkf.bot.enums.PinEnums;
import cn.tpkf.bot.utils.GpioConfigUtils;
import com.pi4j.context.Context;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmType;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * PWM 蜂鸣器
 * 可通过频率控制
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 10:14
 */
@Getter
@EqualsAndHashCode(callSuper = true)
public class PwmBuzzer extends AbstractPwmDevice {

    private final Pwm pwm;

    private final String name;

    private final Context context;

    private final ReentrantLock lock;

    public PwmBuzzer(Context pi4jContext, PinEnums pinEnums, String name, PwmType pwmType, int initial, int shutdown) {
        this.name = name;
        this.context = pi4jContext;
        pwm = pi4jContext.create(GpioConfigUtils.buildPwmConfig(pi4jContext, pinEnums, name, pwmType, initial, shutdown));
        lock = new ReentrantLock();
    }

    @Override
    public void setUp() {
        on(1000);
    }
}
