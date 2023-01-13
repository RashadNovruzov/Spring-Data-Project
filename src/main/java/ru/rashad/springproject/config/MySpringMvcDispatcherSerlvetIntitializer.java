package ru.rashad.springproject.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/*
 * этот класс заменят web.xml веб сервер tomcat сперва сканирует этот класс и мы здесь создаем dispatcherservlet чтобы запросы поступали на наш spring mvc приложение
 */
public class MySpringMvcDispatcherSerlvetIntitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	@Override//что делает и что такое класс Class а также что за синтаксис Class[] {SpringConfig.class}? ответ ==> https://ru.stackoverflow.com/questions/617045/%D0%A7%D1%82%D0%BE-%D0%B7%D0%B0-%D1%82%D0%B8%D0%BF-class-%D0%B2-java
	protected Class<?>[] getServletConfigClasses() {
		Class<?>[] classes = {SpringConfig.class};
		return classes;// new Class[] {SpringConfig.class} можно и так
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};//это говорит веб серверу что любые запросы поступающие на сервер поступали в наш DispatcherServlet и приложение
	}

	
	
}
