package cn.tpkf.bot.manager;

import cn.tpkf.bot.function.Function;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 18 下午 06:41
 */
public class FunctionManager {

    private final Map<String, Function> functionMapping = new ConcurrentHashMap<>();

    public void register(Function... function) {
        for (Function fun : function) {
            functionMapping.put(fun.getName(), fun);
        }
    }

    public void register(Function function) {
        functionMapping.put(function.getName(), function);
    }

    public Function getFunction(String name) {
        return functionMapping.get(name);
    }

    public Function remove(String name) {
        return functionMapping.remove(name);
    }
}
