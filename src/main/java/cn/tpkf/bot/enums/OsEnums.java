package cn.tpkf.bot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Operational status or single-shot conversion start
 * This bit determines the operational status of the device. OS can only be written
 * when in power-down state and has no effect when a conversion is ongoing.
 *
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 01:41
 */
@Getter
@AllArgsConstructor
public enum OsEnums {

    /**
     * When writing: start a single conversion (when in power-down state)
     */
    WRITE_START(0b1000_0000_0000_0000),

    /**
     * When reading: Device is currently performing a conversion
     */
    READ_CONV(0b0000_0000_0000_0000),

    /**
     * When reading: Device is not currently performing a conversion
     */
    READ_NO_CONV(0b1000_0000_0000_0000),

    /**
     * With an AND operation, all other parameters will be set to 0
     */
    CLR_OTHER_CONF_PARAM(0b1000_0000_0000_0000),

    /**
     * With an AND operation, the current parameters will be set to 0
     * all other parameters remain unchanged
     */
    CLR_CURRENT_CONF_PARAM(0b0111_1111_1111_1111);

    /**
     * Operational status or single-shot conversion start
     */
    private final int value;

}
