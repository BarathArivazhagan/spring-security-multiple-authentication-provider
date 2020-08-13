package com.barath.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	@Qualifier("userDetailsService")
	private OAuth2UserDetailsService userDetailsService;
	
	@Override
	@Order(Ordered.HIGHEST_PRECEDENCE)
	protected void configure(final HttpSecurity http) throws Exception
	{
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()//
				.csrf().disable()//
				.authorizeRequests()//
				.antMatchers("/oauth/token").permitAll()
				.antMatchers("/actuator/**").permitAll()//
				.anyRequest().authenticated().and()//
				.formLogin();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.ldapAuthentication()
	     .userDnPatterns("uid={0},ou=people")
	        .groupSearchBase("ou=groups")
	        .contextSource()
	          .url("ldap://localhost:389/dc=example,dc=org")
	          .and()
	        .passwordCompare()
	          .passwordEncoder(new BCryptPasswordEncoder())
	          .passwordAttribute("userPassword");
		auth.authenticationProvider(dbAuthenticationProvider());

	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
	
	@Bean
	public DaoAuthenticationProvider dbAuthenticationProvider() {
		DaoAuthenticationProvider dbProvider = new DaoAuthenticationProvider();
		dbProvider.setUserDetailsService(userDetailsService);
		dbProvider.setPasswordEncoder(passwordEncoder());
		return dbProvider;
	}
    


}
