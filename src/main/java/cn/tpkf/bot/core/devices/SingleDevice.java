package cn.tpkf.bot.core.devices;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 06:08
 */
public interface SingleDevice extends Device {


    /**
     * 是否上锁
     * @return boolean
     */
    boolean isLocked();
}
