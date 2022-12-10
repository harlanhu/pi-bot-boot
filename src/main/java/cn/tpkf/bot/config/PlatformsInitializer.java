package cn.tpkf.bot.config;

import cn.hutool.core.io.resource.ResourceUtil;
import com.pi4j.Pi4J;
import com.pi4j.common.Descriptor;
import com.pi4j.context.Context;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 09 下午 09:01
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PlatformsInitializer {

    @Bean
    public Context pi4jContext() {
        String banner = ResourceUtil.readUtf8Str("static/banner.txt");
        Context pi4jContext = Pi4J.newAutoContext();
        Descriptor describe = pi4jContext.platform().describe();
        log.info("\n{}\nId: {}\nName: {}\nCategory: {}\nQuantity: {}\nParent: {}\nValue: {}\n",
                banner, describe.id(), describe.name(), describe.category(), describe.quantity(), describe.parent(),
                describe.value());
        return pi4jContext;
    }
}
