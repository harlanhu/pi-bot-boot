package cn.tpkf.bot.core.manager;

import cn.tpkf.bot.core.devices.Device;
import cn.tpkf.bot.core.devices.digital.output.DigitalOutputDevice;
import cn.tpkf.bot.core.devices.i2c.adda.Pcf8591;
import cn.tpkf.bot.core.devices.i2c.display.oled.OledDisplayDevice;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/15
 */
@Data
@Slf4j
public class DeviceManager {

    private final Executor asyncExecutor;

    private final DigitalOutputDevice buzzer;

    private final Pcf8591 pcf8591;

    private final OledDisplayDevice oled;

    private final List<Device> devices = new ArrayList<>();

    public DeviceManager(Executor asyncExecutor, DigitalOutputDevice buzzer, Pcf8591 pcf8591, OledDisplayDevice oled) {
        this.asyncExecutor = asyncExecutor;
        this.buzzer = buzzer;
        this.pcf8591 = pcf8591;
        this.oled = oled;
        setUpDevice();
    }

    @SneakyThrows
    private void setUpDevice() {
        log.info("正在启动设备...");
        devices.add(buzzer);
        devices.add(pcf8591);
        devices.add(oled);
        CountDownLatch latch = new CountDownLatch(devices.size());
        devices.forEach(device -> asyncExecutor.execute(() -> {
            device.setUp();
            latch.countDown();
            log.info("{}: 启动完成...", device.getName());
        }));
        latch.await();
        log.info("所有设备启动完成...");
    }

    public int getDeviceSize() {
        return devices.size();
    }
}