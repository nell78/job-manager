package shjeon.jobManager

import javax.servlet.Filter
import javax.servlet.ServletContext
import javax.servlet.ServletException
import javax.servlet.ServletRegistration

import org.h2.server.web.WebServlet
import org.springframework.web.filter.CharacterEncodingFilter
import org.springframework.web.filter.DelegatingFilterProxy
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

/**
 * web.xml을 대신하여 웹 애플리케이션 설정 및 초기화를 수행하는 클래스.
 * 
 * @author 송영환
 */
class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.initParameters = [
			"spring.profiles.active": "development"
//			"spring.profiles.active": "production"
		]
	}
	
	@Override
	protected Class[] getRootConfigClasses() {
		[AppConfig] as Class[]
	}

	@Override
	protected Class[] getServletConfigClasses() {
		[WebConfig] as Class[]
	}

	@Override
	protected Filter[] getServletFilters() {
		[
			new CharacterEncodingFilter(encoding: "UTF-8"),
			new DelegatingFilterProxy(targetFilterLifecycle: true) // Apache Shiro
		] as Filter[]
	}

	@Override
	protected String[] getServletMappings() {
		["/"] as String[]
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		super.onStartup(servletContext)

		// H2 콘솔 서블릿 등록
		def h2c = servletContext.addServlet("h2console", new WebServlet())
//		h2c.initParameters = [
//			webAllowOthers: "",
//			trace: ""
//		]
		h2c.loadOnStartup = 1
		h2c.addMapping("/dbconsole/*")
	}
}
