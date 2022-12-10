package cn.tpkf.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * gpio bcm number
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 10:17
 */
@Getter
@AllArgsConstructor
public enum PinEnums {

    /**
     * gpio bcm number
     */
    SDA1(2),

    SCL1(2),

    D4(4),

    TXD(14),

    RXD(15),

    D17(17),

    PWM18(18),

    D27(27),

    D22(22),

    D23(23),

    D24(24),

    MOSI(10),

    MISO(9),

    D25(25),

    D11(11),

    CEO(8),

    CE1(7),

    D5(5),

    D6(6),

    D16(16),

    D26(26),

    D20(20),

    D21(21),

    PWM12(12),

    PWM13(13),

    PWM19(19);

    private final int vale;
}
