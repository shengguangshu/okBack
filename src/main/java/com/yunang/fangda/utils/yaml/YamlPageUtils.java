package com.yunang.fangda.utils.yaml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Slf4j
@Component
@ConfigurationProperties(prefix = "page")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YamlPageUtils {

    public static int pageSize;
}
