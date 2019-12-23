package com.company.titulo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()
		.antMatchers("/webjars/**", "/css/**", "/fonts/**", "/libs/**", "/js/**").permitAll()
        .anyRequest().authenticated().anyRequest().authenticated()
        //.and().formLogin().permitAll();
		.and().formLogin().loginPage("/login").permitAll()
		.and().logout().logoutSuccessUrl("/login?logout").permitAll();
	}
}
