package com.qcap.core.license;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * license 自定义参数对象
 *
 * @package: com.qcap.core.license
 * @ClassName: LicenseProperties.java
 * @author: 彭浩
 * @date: 2018/11/17 0:22
 * @version: 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "license")
@PropertySource("classpath:/config/license-config.properties")//配置文件路径
@Data
public class LicenseProperties {

    /**
     * 证书subject
     */
    private String subject;

    /**
     * 公钥别称
     */
    private String publicAlias;

    /**
     * 访问公钥库的密码
     */
    private String storePass;

    /**
     * 证书生成路径
     */
    private String licensePath;

    /**
     * 密钥库存储路径
     */
    private String publicKeysStorePath;

}
