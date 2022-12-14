package cn.tpkf.bot.devices.pwm;

import cn.tpkf.bot.enums.PinEnums;
import com.pi4j.context.Context;
import com.pi4j.io.pwm.PwmType;

/**
 * PWM 蜂鸣器
 * 可通过频率控制
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 10:14
 */
public class PwmBuzzer extends AbstractPwmDevice {

    public PwmBuzzer(Context pi4jContext, PinEnums pinEnums, String name, PwmType pwmType, int initial, int shutdown) {
        super(pi4jContext, pinEnums, name, pwmType, initial, shutdown);
    }

    @Override
    public void setUp() {
        on(1000);
    }
}
