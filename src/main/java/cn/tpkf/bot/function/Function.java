package cn.tpkf.bot.function;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/14
 */
public interface Function {

    /**
     * 获取功能名称
     * @return 功能名称
     */
    String getName();

    /**
     * 获取当前状态
     */
    void getState();

    /**
     * 初始化
     */
    void setUp();

    /**
     * 启动功能
     */
    void start();

    /**
     * 停止功能
     */
    void stop();
}
