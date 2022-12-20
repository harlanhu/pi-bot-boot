package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.devices.i2c.display.oled.OledDisplayDevice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 18 下午 07:01
 */
public class DisplayInfoFunction extends AbstractAsyncFunction {

    private final OledDisplayDevice oled;

    private final Long showTime;

    private final TimeUnit timeUnit;

    public DisplayInfoFunction(String name, Executor asyncExecutor, OledDisplayDevice oled) {
        super(name, asyncExecutor);
        this.oled = oled;
        this.showTime = 15L;
        this.timeUnit = TimeUnit.SECONDS;
    }


    public DisplayInfoFunction(String name, Executor asyncExecutor, OledDisplayDevice oled, Long showTime, TimeUnit timeUnit) {
        super(name, asyncExecutor);
        this.oled = oled;
        this.showTime = showTime;
        this.timeUnit = timeUnit;
    }

    @Override
    protected void doSetUp() {
        //DO NOTHING
    }

    @Override
    protected void doRun() {
        displayTime();
    }

    @Override
    protected void doStop() {
        oled.resetDataBuffer();
    }

    private void displayTime() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String dayOfWeek = LocalDate.now().getDayOfWeek().name();
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        oled.setPixel(date + " " + dayOfWeek, 0, 0, true);
        oled.setPixel(time, 0, 30, true);
        oled.display();
    }

    private void displayOuterWeather() {

    }

    private void displayInnerWeather() {

    }
}
