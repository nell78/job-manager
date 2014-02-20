package shjeon.jobManager

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.multipart.commons.CommonsMultipartResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver

/**
 * Spring MVC 관련 설정.
 * 
 * @author 송영환
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = WebConfig, includeFilters = @ComponentScan.Filter(Controller))
class WebConfig extends WebMvcConfigurerAdapter {
	protected Logger logger = LoggerFactory.getLogger(WebConfig)
	
	@Bean
	def modelInterceptor() {
		new ModelInterceptor()
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(modelInterceptor())
	}

	@Bean
	FreeMarkerConfigurer freeMarkerConfigurer() {
		def fmc = new FreeMarkerConfigurer()
		fmc.templateLoaderPath = "/WEB-INF/views/"
		fmc
	}

	@Bean
	FreeMarkerViewResolver viewResolver() {
		def vr = new FreeMarkerViewResolver()
		vr.cache = false
		vr.prefix = ""
		vr.suffix = ".ftl"
		vr.contentType = "text/html; charset=UTF-8"
		vr.requestContextAttribute = "rc"
		vr
	}
	
	@Bean
	def multipartResolver() {
		new CommonsMultipartResolver(
			maxUploadSize: 10 * 1024 * 1024, defaultEncoding: "UTF-8")
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
			.addResourceLocations("/resources/")
	}
}
