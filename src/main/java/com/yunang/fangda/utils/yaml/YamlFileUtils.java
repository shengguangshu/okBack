package com.yunang.fangda.utils.yaml;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: LD
 * @date:
 * @description:
 */
@Component
@ConfigurationProperties(prefix = "file")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YamlFileUtils {

    private static String fileModel;
    private static String dowPath;
    private static String upPath;
    private static String upPrefix;
}
