package snippet;

public class Snippet {
	spring.application.name=GDJ94_SpringBoot
	
	## port 설정
	server.port=80
	
	## JSP 경로 설정
	spring.mvc.view.prefix=/WEB-INF/views/
	spring.mvc.view.suffix=.jsp
	
	## Mybatis 설정
	spring.datasource.hikari.username=user01
	spring.datasource.hikari.password=user01
	spring.datasource.url=jdbc:mariadb://15.164.214.111:3306/user01
	spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
	
	mybatis.configuration.map-underscore-to-camel-case=true
	
	#단, 같은 클래스명이 존재하면 error
	mybatis.type-aliases-
}

