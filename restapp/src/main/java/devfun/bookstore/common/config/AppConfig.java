
package devfun.bookstore.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;

@EnableTransactionManagement
@MapperScan("devfun.bookstore.common.mapper")
@ComponentScan(basePackages = { "devfun.bookstore.common.service" }, useDefaultFilters = false, includeFilters = {
		@Filter(Service.class) })
@Configuration
public class AppConfig implements TransactionManagementConfigurer {

	@Bean
	public DataSource dataSource() {
		return proxyDataSource(new EmbeddedDatabaseBuilder().setName("testdb") // DB 이름 설정
				.setType(EmbeddedDatabaseType.HSQL) // DB 종류 설정
				.addScript("schema.sql") // 스키마 스크립트 추가
				.addScript("data.sql") // 데이터 스크립트 추가
				.build());
	}

	private DataSource proxyDataSource(DataSource dataSource) {
		
		// log4jdbc 설정

//		Log4jdbcProxyDataSource proxyDataSource = new Log4jdbcProxyDataSource(dataSource);
//		Log4JdbcCustomFormatter formatter = new Log4JdbcCustomFormatter();
//		formatter.setLoggingType(LoggingType.MULTI_LINE);
//		formatter.setSqlPrefix("SQL         :  ");
//		proxyDataSource.setLogFormatter(formatter);
		
		 // log4jdbc를 사용하여 데이터소스 래핑
        DataSource spyDataSource = new DataSourceSpy(dataSource);
        
//        logger.info("DataSource has been set up");
		return spyDataSource;
	}

//	@Bean
//	public DataSource dataSource() {
//		HikariConfig hikariConfig = new HikariConfig();
//		hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
//		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
//		hikariConfig.setUsername("book_ex");
//		hikariConfig.setPassword("book_ex");
//		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//		return dataSource;
//	}
//	출처: https://a-develop.tistory.com/82 [A-develop:티스토리]

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager(); // reference the existing @Bean method above
	}

}
