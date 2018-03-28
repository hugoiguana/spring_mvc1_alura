package br.com.casadocodigo.loja.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class };
	}

	/**
	 * Informa ao SpringMVC que as classes "AppWebConfiguration" e
	 * "JPAConfiguration" seram usadas como classes de configuração do servlet
	 * do SpringMVC.
	 * 
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {};
	}

	/**
	 * Permite que o SpringMVC atenda as requisições a partir da raiz do nosso
	 * projeto (/)
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/**
	 * Possibilita a criação de filtros. Nesse caso estamos usando um filtro
	 * para configurar o encoding das páginas/VIEWS.
	 */
	@Override
	protected Filter[] getServletFilters() {

		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");

		return new Filter[] { encodingFilter,  new OpenEntityManagerInViewFilter() };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.addListener(new RequestContextListener());
		servletContext.setInitParameter("spring.profiles.active", "dev");
	}

}
