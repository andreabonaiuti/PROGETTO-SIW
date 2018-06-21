package it.uniroma3.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import it.uniroma3.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	/*@Qualifier("customUserDetailsService")*/
	private UserDetailsService userDetailsService;
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)

		.passwordEncoder(new BCryptPasswordEncoder())
		.usersByUsernameQuery("SELECT username,password,1 FROM utente where username=?")
		.authoritiesByUsernameQuery("SELECT username,role FROM utente where username=?");
		auth.userDetailsService(new UserDetailsService() {
			@Autowired
			private UserRepository repository;

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				return (UserDetails) this.repository.findByUsername(username);
			}
		});
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		//		.antMatchers("/", "/login", "/registrationform", "/listautenti").permitAll()
			.antMatchers("/allievoForm", "/attivitaForm", "/giornata", "/home", "/iscrizioneForm", "/mostraAttivita", "registrazioneForm").access("hasAuthority('RESPONSABILE')")
			.antMatchers("/listaCentri").access("hasAuthority('USER') or hasAuthority('ADMIN')")
			.anyRequest().permitAll()
			.and()
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/loginOk")
			.permitAll()
			.failureUrl("/login?error")
			.and()
		.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/logoutok")
			.permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403");
			
	}
	
}