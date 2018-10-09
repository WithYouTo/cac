package com.qcap.core.configuration;

import static cn.hutool.core.collection.CollUtil.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2配置
 *
 * <p>
 * @ApiModel @ApiModelProperty作用在实体类上 可以描述字段信息
 * </p>
 * <p>
 * @Api 作用在类上
 * </p>
 * <a href="http://springfox.github.io/springfox/docs/current/">官网doc</a>
 * 
 * @author yandixuan
 */
@Configuration
@ConditionalOnProperty(prefix = "rest", name = "swaggerEnable", havingValue = "true", matchIfMissing = true)
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				// API所属的组织
				.groupName("whxx")
				// API信息
				.apiInfo(apiInfo())
				// 选择那些路径和api会生成document
				.select()
				// 对所有api 有@ApiOperation的注解进行监控
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
				// 匹配哪些路径
				.paths(PathSelectors.any()).build().directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(newRule(
						typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
						typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(true)
		// .globalResponseMessage(RequestMethod.GET,
		// newArrayList(customerResponseMessage()))
		// .globalResponseMessage(RequestMethod.POST,
		// newArrayList(customerResponseMessage()))
		;
	}

	@Autowired
	private TypeResolver typeResolver;

	@Bean
	public UiConfiguration swaggerUiConfiguration() {
		return UiConfigurationBuilder.builder().deepLinking(true)
				// 是否显示OperationApiId
				.displayOperationId(false).defaultModelsExpandDepth(1).defaultModelExpandDepth(1)
				.defaultModelRendering(ModelRendering.EXAMPLE)
				// 是否显示请求时长
				.displayRequestDuration(false)
				// API doc的显示(LIST：列表；NONE：不显示；FULL：全部展开)
				.docExpansion(DocExpansion.NONE).filter(false).maxDisplayedTags(null)
				// API列表排序
				.operationsSorter(OperationsSorter.ALPHA).showExtensions(false)
				// 标签排序
				.tagsSorter(TagsSorter.ALPHA)
				// 允许提交的方法
				.supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS).validatorUrl(null).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("swagger2构建rest api文档")
				// 联系人
				.contact(new Contact("PH", "https://www.baidu.com", "")).description("描述：The Center Demo")
				.version("1.0").build();
	}

	/**
	 * 自定义返回信息
	 */
	private List<ResponseMessage> customerResponseMessage() {
		return newArrayList(new ResponseMessageBuilder().code(500).message("服务器出错").build());
	}
}