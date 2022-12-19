package cn.tpkf.bot.core.devices.i2c.display.oled.font;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 11 下午 12:23
 */
@Getter
@AllArgsConstructor
public enum Rotation {

    /**
     * 0°
     */
    DEG_0(0),

    /**
     * 90°
     */
    DEG_90(90),

    /**
     * 180°
     */
    DEG_180(180),

    /**
     * 270°
     */
    DEG_270(270);

    private final int value;
}
