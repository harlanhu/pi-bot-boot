package cn.tpkf.bot.controller;

import cn.tpkf.bot.core.commend.DisplayTimeCommend;
import cn.tpkf.bot.core.devices.digital.output.DigitalOutputDevice;
import cn.tpkf.bot.core.devices.i2c.adda.Pcf8591;
import cn.tpkf.bot.core.devices.i2c.display.oled.OledDisplayDevice;
import cn.tpkf.bot.core.function.AsyncFunction;
import cn.tpkf.bot.core.function.Function;
import cn.tpkf.bot.core.manager.DeviceManager;
import cn.tpkf.bot.entity.base.ResultEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 08:58
 */
@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final DeviceManager deviceManager;

    private final Executor asyncExecutor;

    @GetMapping("/buzzer")
    public ResultEntity<Boolean> toggle() {
        DigitalOutputDevice buzzer = deviceManager.getBuzzer();
        return ResultEntity.success(buzzer.toggle());
    }

    @GetMapping("/pcf")
    public ResultEntity<Map<String, Double>> pcf() {
        Pcf8591 pcf8591 = deviceManager.getPcf8591();
        Map<String, Double> mapping = new HashMap<>(4);
        mapping.put("AIN0", pcf8591.readAin0());
        mapping.put("AIN1", pcf8591.readAin1());
        mapping.put("AIN2", pcf8591.readAin2());
        mapping.put("AIN3", pcf8591.readAin3());
        return ResultEntity.success(mapping);
    }

    @GetMapping("/oled12864/{x}/{y}/{text}")
    public ResultEntity<Void> oled12864(@PathVariable Integer x, @PathVariable Integer y, @PathVariable String text) {
        OledDisplayDevice oled12864 = deviceManager.getOled();
        oled12864.displayStr(text, x, y, true);
        return ResultEntity.success();
    }

    @GetMapping("/function")
    public ResultEntity<Void> function() {
        Function function = new AsyncFunction("display", asyncExecutor, null, null, new DisplayTimeCommend(deviceManager.getOled()));
        function.run();
        return ResultEntity.success();
    }
}
