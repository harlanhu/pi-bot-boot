package cn.tpkf.bot.controller;

import cn.tpkf.bot.core.DeviceManager;
import cn.tpkf.bot.devices.digital.output.Buzzer;
import cn.tpkf.bot.devices.i2c.adda.Pcf8591;
import cn.tpkf.bot.devices.i2c.display.oled.Oled12864;
import cn.tpkf.bot.entity.base.ResultEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    @GetMapping("/buzzer")
    public ResultEntity<Boolean> toggle() {
        Buzzer buzzer = deviceManager.getBuzzer();
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
    public ResultEntity<Object> oled12864(@PathVariable Integer x, @PathVariable Integer y, @PathVariable String text) {
        Oled12864 oled12864 = deviceManager.getOled12864();
        oled12864.clearAndDrawString(text, x, y, true);
        return ResultEntity.success();
    }

}
