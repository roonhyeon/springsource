package com.spring.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// root-context.xml 대신 사용하는 클래스
@EnableTransactionManagement // @Transactional 대신
@EnableScheduling
@MapperScan("com.spring.mapper") // root-context의 마지막 줄 대신
@Configuration // 이 클래스는 일반 클래스가 아니라 환경설정과 관련있는 클래스라는 것을 알려주는 어노테이션
@ComponentScan({"com.spring.service", "com.spring.schedule"}) // root-context의 첫번째 줄 대신
public class RootConfig {
	
	// HikariCP 코드 대신
	@Bean // <bean> 대신하는 어노테이션
	public DataSource dataSource() {
		HikariConfig hikariConfig=new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1522:xe");
		hikariConfig.setUsername("javadb");
		hikariConfig.setPassword("12345");
		
		HikariDataSource dataSource=new HikariDataSource(hikariConfig);
		return dataSource;
	}
	
	// mybatis 설정 대신
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		
		return sqlSessionFactoryBean.getObject();
	}
	
	// 스프링 트랜잭션 매니저 등록 대신
	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
