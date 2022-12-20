package cn.tpkf.bot.core.commend;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/20
 */
public interface Commend {

    /**
     * 获取指令名称
     * @return 指令名称
     */
    String getName();

    /**
     * 执行命令
     */
    void execute();
}
