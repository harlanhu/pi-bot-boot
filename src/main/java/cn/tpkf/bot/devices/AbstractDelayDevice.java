package cn.tpkf.bot.devices;

import com.pi4j.context.Context;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 10:37
 */
@Slf4j
public abstract class AbstractDelayDevice extends AbstractDevice implements Device {

    protected AbstractDelayDevice(Context pi4jContext, String name) {
        super(pi4jContext, name);
    }

    /**
     * 延迟
     *
     * @param time     时间
     * @param timeUnit 单位
     */
    protected void delay(long time, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            log.error("fail to delay: {} ---- {}", e.getMessage(), e.getCause());
            Thread.currentThread().interrupt();
        }
    }
}
