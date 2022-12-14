package cn.tpkf.bot.controller;

import cn.tpkf.bot.devices.digital.output.Buzzer;
import cn.tpkf.bot.devices.i2c.adda.Pcf8591;
import cn.tpkf.bot.devices.i2c.display.oled.SSD1306;
import cn.tpkf.bot.entity.base.ResultEntity;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 08:58
 */
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final Buzzer buzzer;

    private final Pcf8591 pcf8591;

    private final SSD1306 ssd12832;

    @GetMapping("/on")
    public ResultEntity<Object> on() {
        buzzer.on();
        return ResultEntity.success();
    }

    @GetMapping("/off")
    public ResultEntity<Object> off() {
        buzzer.off();
        return ResultEntity.success();
    }

    @GetMapping("/buzzer")
    public ResultEntity<Boolean> toggle() {
        return ResultEntity.success(buzzer.toggle());
    }

    @GetMapping("/pcf")
    public ResultEntity<Map<String, Double>> pcf() {
        Map<String, Double> mapping = new HashMap<>(4);
        mapping.put("AIN0", pcf8591.readAin0());
        mapping.put("AIN1", pcf8591.readAin1());
        mapping.put("AIN2", pcf8591.readAin2());
        mapping.put("AIN3", pcf8591.readAin3());
        return ResultEntity.success(mapping);
    }

    @GetMapping("/ssd/{text}")
    public ResultEntity<Object> ssd(@PathVariable String text) {
        ssd12832.drawString(text, 2, 2, true);
        ssd12832.updateDataBuffer();
        return ResultEntity.success();
    }

}
