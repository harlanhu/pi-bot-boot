package cn.tpkf.bot.entity.base.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author Harlan
 * @email isharlan.hu@gmail.com
 * @date 2022 12 25 下午 06:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class WeatherDto implements Serializable {

    private Integer status;

    private Integer count;

    private String info;

    @JSONField(name = "infocode")
    private Integer infoCode;

    private List<WeatherDetails> lives;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    private static class WeatherDetails implements Serializable {

        private String province;

        private String city;

        @JSONField(name = "adcode")
        private Integer adCode;

        private String weather;
    }
}
