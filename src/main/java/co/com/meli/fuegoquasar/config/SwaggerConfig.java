package co.com.meli.fuegoquasar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import java.time.LocalDate;

@Configuration
public class SwaggerConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	private final String baseUrl = "/";

	@Bean
	public Docket eDesignApi(SwaggerConfigProperties swaggerConfigProperties) {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo(swaggerConfigProperties))
				.enable(Boolean.valueOf(swaggerConfigProperties.getEnabled())).select()
				.apis(RequestHandlerSelectors.basePackage("co.com.meli.fuegoquasar.controller"))
				.paths(PathSelectors.any()).build().pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(Boolean.valueOf(swaggerConfigProperties.getUseDefaultResponseMessages()))
				.enableUrlTemplating(Boolean.valueOf(swaggerConfigProperties.getEnableUrlTemplating()));
	}

	@Bean
	UiConfiguration uiConfig(SwaggerConfigProperties swaggerConfigProperties) {
		return UiConfigurationBuilder.builder().deepLinking(Boolean.valueOf(swaggerConfigProperties.getDeepLinking()))
				.displayOperationId(Boolean.valueOf(swaggerConfigProperties.getDisplayOperationId()))
				.defaultModelsExpandDepth(Integer.valueOf(swaggerConfigProperties.getDefaultModelsExpandDepth()))
				.defaultModelExpandDepth(Integer.valueOf(swaggerConfigProperties.getDefaultModelExpandDepth()))
				.defaultModelRendering(ModelRendering.EXAMPLE)
				.displayRequestDuration(Boolean.valueOf(swaggerConfigProperties.getDisplayRequestDuration()))
				.docExpansion(DocExpansion.NONE).filter(Boolean.valueOf(swaggerConfigProperties.getFilter()))
				.maxDisplayedTags(Integer.valueOf(swaggerConfigProperties.getMaxDisplayedTags()))
				.operationsSorter(OperationsSorter.ALPHA)
				.showExtensions(Boolean.valueOf(swaggerConfigProperties.getShowExtensions()))
				.tagsSorter(TagsSorter.ALPHA).supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
				.validatorUrl(null).build();
	}

	private ApiInfo apiInfo(SwaggerConfigProperties swaggerConfigProperties) {
		return new ApiInfoBuilder().title(swaggerConfigProperties.getTitle())
				.description(swaggerConfigProperties.getDescription()).version(swaggerConfigProperties.getApiVersion())
				.build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String baseUrl = this.baseUrl;
		registry.addResourceHandler(baseUrl + "/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
				.resourceChain(false);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController(baseUrl + "/swagger-ui/")
				.setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/", "/csrf", "/v2/api-docs", "/swagger-resources/configuration/ui", "/configuration/ui",
						"/swagger-resources", "/swagger-resources/configuration/security", "/configuration/security",
						"/swagger-ui.html", "/webjars", "/swagger-ui.html/swagger-resources/configuration/security",
						"/swagger-ui.html/swagger-resources**","/h2-console/**")
				.permitAll();
		httpSecurity.headers().frameOptions().disable();
	}
}
