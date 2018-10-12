package com.qcap.core.configuration;

import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcap.core.filter.AuthFilter;
import com.qcap.core.properties.RestProperties;

/**
 * MVC 配置项实现了 {@link WebMvcConfigurer}
 *
 * @author Zhousheng
 * @date 2017年11月15日 上午10:02:16
 */
@EnableWebMvc
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer, EnvironmentAware {

	private Environment env;

	/**
	 * 配置filter
	 *
	 * @return AuthFilter
	 */
	@Bean
	@ConditionalOnProperty(prefix = RestProperties.REST_PREFIX, name = "coreOpen", havingValue = "true", matchIfMissing = true)
	public AuthFilter jwtAuthenticationTokenFilter() {
		return new AuthFilter();
	}

	/**
	 * @param registry
	 *            跨域注册
	 * @date 2018/6/11
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
				.allowCredentials(false).maxAge(3600);
	}

	/**
	 * 118n 国际化
	 *
	 * @return LocaleResolver
	 */
	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	/**
	 * 注册mvc拦截器
	 *
	 * @param registry
	 *            拦截器注册
	 * @date 2018/6/11
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public MybatisArgumentResolver mybatisArgumentResolver() {
		return new MybatisArgumentResolver();
	}

	/**
	 * @param argumentResolvers
	 *            method参数解决List
	 * @date 2018/6/11
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(mybatisArgumentResolver());
	}

	@Override
	public void setEnvironment(Environment environment) {
		this.env = environment;
	}

	private class MybatisArgumentResolver implements HandlerMethodArgumentResolver {
		@Override
		public boolean supportsParameter(MethodParameter methodParameter) {
			return methodParameter.getParameterType().equals(IPage.class);
		}

		@Override
		public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
				NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
			String va1 = env.getProperty("mybatis.iPage.page-parameter", "page");
			String va2 = env.getProperty("mybatis.iPage.page-size", "limit");
			int pageNumber = NumberUtils.toInt(nativeWebRequest.getParameter(va1));
			int pageSize = NumberUtils.toInt(nativeWebRequest.getParameter(va2));
			return new Page<>(pageNumber, pageSize);
		}
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
