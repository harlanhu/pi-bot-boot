package cn.tpkf.bot.devices;

import cn.tpkf.bot.devices.Device;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 10:37
 */
@Slf4j
public abstract class AbstractDelayDevice implements Device {

    /**
     * 延迟
     * @param milliSeconds 毫秒数
     */
    protected void delay(long milliSeconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliSeconds);
        } catch (InterruptedException e) {
            log.error("fail to delay: {} ---- {}", e.getMessage(), e.getCause());
            Thread.currentThread().interrupt();
        }
    }
}
