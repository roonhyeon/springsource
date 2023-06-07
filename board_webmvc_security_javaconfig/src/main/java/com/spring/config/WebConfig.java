package com.spring.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// web.xml 대신 사용하는 클래스
@Configuration
public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() { // root-context.xml 대신
		return new Class[] {RootConfig.class, SecurityConfig.class}; // RootConfig 불러와라
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { // servlet-context.xml 대신
		return new Class[] {ServletConfig.class}; // ServletConfig 불러와라
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
		
//	web.xml의 이 코드 대신
//  <servlet-mapping>
//		<servlet-name>appServlet</servlet-name>
//		<url-pattern>/</url-pattern>
//	</servlet-mapping>
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter filter=new CharacterEncodingFilter();
		filter.setEncoding("utf-8");
		filter.setForceEncoding(true);
		return new Filter[] {filter};
		
//		web.xml의 이 코드 대신
//		<!-- 한글 입력시 깨지지 않고 출력되기 위해 스프링프레임워크에서 제공하는 한글 필터 -->
//		<filter>
//		     <filter-name>encodingFilter</filter-name>
//		     <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
//		     <init-param>
//		         <param-name>encoding</param-name>
//		         <param-value>UTF-8</param-value>
//		     </init-param>
//		</filter>
//		<filter-mapping>
//		     <filter-name>encodingFilter</filter-name>
//		     <url-pattern>/*</url-pattern>
//		</filter-mapping>
	}

}
