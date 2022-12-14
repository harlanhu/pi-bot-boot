package cn.tpkf.bot.core.function;

import cn.tpkf.bot.core.commend.Commend;
import cn.tpkf.bot.enums.FunctionStateEnums;

import java.util.List;

/**
 * 一个功能由最少1个指令{@link Commend}组成
 * 执行时按指令排列顺序依次执行
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
     * 获取指令
     * @return 指令集
     */
    List<Commend> getCommends();

    /**
     * 获取当前状态
     * @return {@link FunctionStateEnums}
     */
    FunctionStateEnums getState();

    /**
     * 获取当前正在运行的指令
     * @return 指令
     */
    Commend getCuurentCommend();

    /**
     * 获取正在运行的指令下标
     * @return 下标
     */
    Integer getCurrentCommendIndex();

    /**
     * 获取对应下标的指令
     * @param index 下标
     * @return 指令
     */
    Commend getCommend(int index);

    /**
     * 获取下一条指令
     * @return 下一条指令
     */
    Commend getNextCommend();

    /**
     * 执行下一条指令并计数
     * @return 下一条指令
     */
    Commend executeNextCommend();

    /**
     * 获取指令数量
     * @return 指令数量
     */
    int getCommendsSize();

    /**
     * 初始化
     */
    void setUp();

    /**
     * 启动功能
     */
    void run();

    /**
     * 重新开始
     */
    void reRun();

    /**
     * 停止功能
     */
    void stop();

    /**
     * 是否正在运行
     * @return ture false
     */
    boolean isRunning();
}
