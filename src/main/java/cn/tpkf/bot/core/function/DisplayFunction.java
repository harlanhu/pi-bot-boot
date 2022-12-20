package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.manager.DeviceManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executor;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 18 下午 07:01
 */
public class DisplayFunction extends AbstractFunction {

    public DisplayFunction(String name, DeviceManager deviceManager, Executor asyncExecutor) {
        super(name, deviceManager, asyncExecutor);
    }

    @Override
    protected void doSetUp() {
        //DO NOTHING
    }

    @Override
    protected void doRun() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dayOfWeek = LocalDate.now().getDayOfWeek().name();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        deviceManager.getOled12864().displayStr(date + " " + dayOfWeek, 0, 0, true);
        deviceManager.getOled12864().displayStr(time, 0, 30, true);
    }

    @Override
    protected void doStop() {
        deviceManager.getOled12864().resetDataBuffer();
    }
}
