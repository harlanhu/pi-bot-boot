package cn.tpkf.bot.devices;

import com.pi4j.context.Context;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 10 下午 05:26
 */
public interface Device {

    /**
     * 获取名称
     * @return 名称
     */
    String getName();

    /**
     * 获取上下文
     * @return pi4j上下文
     */
    Context getContext();

    /**
     * 设备初始化工作
     */
    void setUp();

}
