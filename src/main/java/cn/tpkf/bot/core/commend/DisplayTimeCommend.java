package cn.tpkf.bot.core.commend;

import cn.tpkf.bot.core.devices.i2c.display.oled.OledDisplayDevice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/20
 */
public class DisplayTimeCommend extends AbstractDisplayCommend {

    public DisplayTimeCommend(OledDisplayDevice oledDisplayDevice) {
        super(oledDisplayDevice);
    }

    @Override
    public void execute() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dayOfWeek = LocalDate.now().getDayOfWeek().name();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        oledDisplayDevice.setPixel(date + " " + dayOfWeek, 0, 0, true);
        oledDisplayDevice.setPixel(time, 0, 30, true);
        oledDisplayDevice.display();
    }
}
