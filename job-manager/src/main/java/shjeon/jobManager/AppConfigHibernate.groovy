package shjeon.jobManager

import org.apache.commons.dbcp.BasicDataSource
import org.hibernate.SessionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.hibernate4.HibernateTransactionManager
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement


/**
 * Hibernate 및 DB 관련 설정.
 * 
 * @author 송영환
 */
@Configuration
@EnableTransactionManagement
class AppConfigHibernate {
	@Bean
	BasicDataSource dataSource() {
		def bds = new BasicDataSource()
		bds.driverClassName = org.h2.Driver.name
		bds.url = "jdbc:h2:~/.h2/job-manager;AUTO_SERVER=TRUE"
		bds
	}
	
	@Bean
	SessionFactory sessionFactory() {
		def b = new LocalSessionFactoryBuilder(dataSource())
		b.scanPackages(this.class.package.name)
		b.setProperty("hibernate.hbm2ddl.auto", "update")
		b.buildSessionFactory()
	}
	
	@Bean
	PlatformTransactionManager transactionManager() {
		def tm = new HibernateTransactionManager()
		tm.sessionFactory = sessionFactory()
		tm
	}
}
