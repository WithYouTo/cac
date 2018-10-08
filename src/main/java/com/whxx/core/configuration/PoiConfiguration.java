package com.whxx.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.BeanNameViewResolver;

/**
 * @author yandixuan
 */
@Configuration
@ComponentScan(basePackages = {"cn.afterturn.easypoi.view"})
public class PoiConfiguration
{
    /**
     * 通过 order 属性来定义视图解析器的优先级, order 值越小优先级越高
     *
     * @return resolver 视图跳转
     */
    @Bean
    public BeanNameViewResolver beanNameViewResolver()
    {
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        resolver.setOrder(10);
        return resolver;
    }
}
