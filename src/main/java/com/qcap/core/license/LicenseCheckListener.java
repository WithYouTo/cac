package com.qcap.core.license;


import com.whxx.license.LicenseVerify;
import com.whxx.license.LicenseVerifyParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;

/**
 * 在项目启动时安装校验证书
 *
 * @author zifangsky
 * @date 2018/4/24
 * @since 1.0.0
 */
@Component
@Slf4j
@Order(HIGHEST_PRECEDENCE)
public class LicenseCheckListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    LicenseProperties licenseProperties;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println(licenseProperties.toString());
//        root application context 没有parent
        ApplicationContext context = event.getApplicationContext().getParent();
        if(context == null) {
            log.info("***************** 开始安装校验证书 *****************");
            if(StringUtils.isNotBlank(licenseProperties.getLicensePath())) {

                LicenseVerifyParam licenseVerifyParam = new LicenseVerifyParam();
                BeanUtils.copyProperties(licenseProperties,licenseVerifyParam);

                LicenseVerify licenseVerify = new LicenseVerify();
//                1.安装证书
                try {
                    licenseVerify.install(licenseVerifyParam);
                } catch (Exception e) {
                    log.error("证书安装失败,原因:{}", e.getMessage());
                    log.error("***************** 证书安装失败 *****************");
                    System.exit(1);
                }
//                2.校验证书
                try {
                    licenseVerify.verify();
                } catch (Exception e) {
                    log.error("证书校验失败,原因:{}", e.getMessage());
                    log.error("***************** 证书校验失败 *****************");
                    System.exit(1);
                }

                log.info("***************** 证书安装校验成功 *****************");
            } else {
                log.error("未配置授权文件(license.lic)路径");
                log.error("***************** 证书安装校验失败 *****************");
                System.exit(1);
            }
        }
    }
}
