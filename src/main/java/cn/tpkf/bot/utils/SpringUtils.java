package cn.tpkf.bot.utils;

import org.springframework.context.ApplicationContext;

/**
 * @author Harlan
 * @email isharlan.hu@gmali.com
 * @date 2022/12/20
 */
public class SpringUtils {

    private static ApplicationContext appContext;

    public static void setAppContext(ApplicationContext appContext) {
        SpringUtils.appContext = appContext;
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }

}
