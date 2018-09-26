package com.qcap.core.config;


import com.qcap.core.properties.RestProperties;
import com.qcap.core.filter.AuthFilter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.servlet.config.annotation.*;


/**
 * 
 * @ClassName: WebConfig 
 * @Description: 系统配置
 * @author: Zhousheng
 * @date: 2017年11月15日 上午10:02:16
 */
@Configuration
@EnableRedisHttpSession
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    @ConditionalOnProperty(prefix = RestProperties.REST_PREFIX, name = "coreOpen", havingValue = "true", matchIfMissing = true)
    public AuthFilter jwtAuthenticationTokenFilter() {
        return new AuthFilter();
    }
    
    @Override  
    public void addCorsMappings(CorsRegistry registry) {  
        registry.addMapping("/**")
                .allowedOrigins("*")  
                .allowCredentials(true)  
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")  
                .maxAge(3600);  
    }  

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    /**
     * druidServlet注册
     */
   /* @Bean
    public ServletRegistrationBean druidServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new StatViewServlet());
        registration.addUrlMappings("/druid/*");
        return registration;
    }*/

    /**
     * druid监控 配置URI拦截策略
     * @return
     */
    /*@Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter(
                "exclusions","/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid,/druid/*");
        //用于session监控页面的用户名显示 需要登录后主动将username注入到session里
        filterRegistrationBean.addInitParameter("principalSessionName","username");
        return filterRegistrationBean;
    }*/

    /**
     * druid数据库连接池监控
     */
    /*@Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }*/


   /* @Bean
    public JdkRegexpMethodPointcut druidStatPointcut(){
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String patterns = "com.whxx.auth.service.*";
        //可以set多个
        druidStatPointcut.setPatterns(patterns);
        return druidStatPointcut;
    }*/

    /**
     * druid数据库连接池监控
     */
    /*@Bean
    public BeanTypeAutoProxyCreator beanTypeAutoProxyCreator() {
        BeanTypeAutoProxyCreator beanTypeAutoProxyCreator = new BeanTypeAutoProxyCreator();
        beanTypeAutoProxyCreator.setTargetBeanType(DruidDataSource.class);
        beanTypeAutoProxyCreator.setInterceptorNames("druidStatInterceptor");
        return beanTypeAutoProxyCreator;
    }*/

    /**
     * druid 为druidStatPointcut添加拦截
     * @return
     */
    /*@Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }*/



}
