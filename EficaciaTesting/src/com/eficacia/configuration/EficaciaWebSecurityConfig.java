package com.eficacia.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.eficacia.security.CustomAuthenticationFailureHandler;
import com.eficacia.security.CustomAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class EficaciaWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("customAuthenticationSuccessHandler")
	private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	@Qualifier("customAuthenticationFailureHandler")
	private CustomAuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().withUser("user").password("12345").roles("ADMIN, EJECUTIVO");

		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
		http.authorizeRequests().antMatchers("/listarAgendas",
				"/cargaMasiva", "/eliminacionMasiva", "/agregarAgenda", "/inicio")
				.access("hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')");
		http.authorizeRequests().antMatchers("/listarUsuarios", "/agregarUsuario", 
				"/editarUsuario/*" ,"/editarAgenda/*" , "/resetPassword")
				.access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/j_spring_security_check").loginPage("/login")
				.defaultSuccessUrl("/inicio").failureUrl("/login?error=true").usernameParameter("soeid")
				.passwordParameter("contrasena")
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

		http.authorizeRequests().and().formLogin().successHandler(authenticationSuccessHandler);
		http.authorizeRequests().and().formLogin().failureHandler(authenticationFailureHandler);
		http.sessionManagement().invalidSessionUrl("/login");
		http.sessionManagement().maximumSessions(2);
	}
}
