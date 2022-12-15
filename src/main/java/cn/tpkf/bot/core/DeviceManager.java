package cn.tpkf.bot.core;

import cn.tpkf.bot.devices.Device;
import cn.tpkf.bot.devices.digital.output.Buzzer;
import cn.tpkf.bot.devices.i2c.adda.Pcf8591;
import cn.tpkf.bot.devices.i2c.display.oled.SSD1306;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/15
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DeviceManager {

    private final Executor asyncExecutor;

    private final Buzzer buzzer;

    private final Pcf8591 pcf8591;

    private final SSD1306 oled12632;

    private final List<Device> devices = new ArrayList<>();

    @PostConstruct
    private void postConstruct() {
        log.info("DeviceManager 正在初始化");
        setUpDevice();
        log.info("DeviceManager 初始化完成");
    }

    private void setUpDevice() {
        log.info("正在启动设备...");
        devices.add(buzzer);
        devices.add(pcf8591);
        devices.add(oled12632);
        devices.forEach(device -> asyncExecutor.execute(() -> {
            device.setUp();
            log.info("{}: 启动完成...", device.getName());
        }));
        log.info("所有启动完成...");
    }
}
