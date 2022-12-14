package cn.tpkf.bot.devices.pwm;

import cn.tpkf.bot.devices.AbstractDelayDevice;
import cn.tpkf.bot.enums.PinEnums;
import com.pi4j.context.Context;
import com.pi4j.io.pwm.Pwm;
import com.pi4j.io.pwm.PwmConfig;
import com.pi4j.io.pwm.PwmType;
import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 05:58
 */
@Getter
public abstract class AbstractPwmDevice extends AbstractDelayDevice implements PwmDevice {

    protected final Pwm pwm;

    protected final ReentrantLock lock;

    protected AbstractPwmDevice(Context pi4jContext, PinEnums pinEnums, String name, PwmType pwmType, int initial, int shutdown) {
        super(pi4jContext, name);
        this.pwm = pi4jContext.create(buildPwmConfig(pi4jContext, pinEnums, name, pwmType, initial, shutdown));
        this.lock = new ReentrantLock();
    }

    protected PwmConfig buildPwmConfig(Context pi4j, PinEnums pin, String name, PwmType pwmType, int initial, int shutdown) {
        return Pwm.newConfigBuilder(pi4j)
                .id("BCM-" + pin)
                .name(name)
                .address(pin.getVale())
                .pwmType(pwmType)
                .initial(initial)
                .shutdown(shutdown)
                .provider("pigpio-pwm")
                .build();
    }

    public void on(int frequency) {
        on(frequency, 1000);
    }

    public void on(int frequency, long duration) {
        lock.lock();
        if (frequency <= 0 || duration <= 0) {
            off();
            return;
        }
        lock.lock();
        try {
            getPwm().on(50, frequency);
            delay(duration);
            off();
        } finally {
            lock.unlock();
        }
    }

    public void off() {
        getPwm().off();
    }

    @Override
    public boolean isLocked() {
        return this.lock.isLocked();
    }
}
