package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.manager.DeviceManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 18 下午 07:01
 */
public class DisplayFunction extends AbstractFunction {

    public DisplayFunction(String name, DeviceManager deviceManager) {
        super(name, deviceManager);
    }

    @Override
    protected void doSetUp() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dayOfWeek = LocalDate.now().getDayOfWeek().name();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        deviceManager.getOled12864().clearAndDrawString(date + " " + dayOfWeek, 0, 0, true);
        deviceManager.getOled12864().clearAndDrawString(time, 0, 30, true);
    }

    @Override
    protected void doRun() {

    }

    @Override
    protected void doStop() {

    }
}
