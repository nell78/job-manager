package shjeon.jobManager

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller

/**
 * 스프링 최상위 컨텍스트 설정. Hibernate 설정과 Shiro 설정을 import한다.
 * 
 * @author 송영환
 */
@Configuration
@ComponentScan(basePackageClasses = AppConfig, excludeFilters = @ComponentScan.Filter(Controller))
@Import([AppConfigHibernate, AppConfigShiro])
class AppConfig {
}
