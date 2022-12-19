package cn.tpkf.bot.core.devices;

import com.pi4j.context.Context;
import lombok.Getter;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/13
 */
@Getter
public abstract class AbstractDevice implements Device {

    protected final String name;

    protected final Context context;

    protected AbstractDevice(Context pi4jContext, String name) {
        this.context = pi4jContext;
        this.name = name;
    }
}
