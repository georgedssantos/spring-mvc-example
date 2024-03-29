package com.company.titulo;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class GestaoTituloApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoTituloApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}
	
	/*
	 * @Configuration public static class WebMvcConfig implements WebMvcConfigurer {
	 * 
	 * @Override public void addViewControllers(ViewControllerRegistry registry) {
	 * registry.addRedirectViewController("/", "pages/pesquisarTitulos"); }
	 * 
	 * }
	 */

}
