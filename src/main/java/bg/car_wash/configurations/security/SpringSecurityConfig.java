package bg.car_wash.configurations.security;

import bg.car_wash.areas.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userService).passwordEncoder(getBCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/", "/user/register", "/bootstrap/**", "/jquery/**", "/js/**").permitAll()
				.antMatchers("/user/**", "/car/**", "/customer/**", "/json/**", "/activity/**", "/service/**").authenticated()
				.antMatchers("/car/delete/**").access("hasRole('ADMIN') OR hasRole('PAYMASTER')")
				.anyRequest().authenticated()
				.and()
				.formLogin().loginPage("/user/login").permitAll()
				.usernameParameter("email")
				.passwordParameter("password")
//                .and()
//                .rememberMe()
//                .rememberMeCookieName("RememberMeFromLecture")
//                .rememberMeParameter("rememberMe")
//                .key("GolqmTaina")
//                .tokenValiditySeconds(1000)
				.and()
				.logout().logoutSuccessUrl("/user/logout").permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/unauthorized")
				.and()
				.csrf().disable();
	}

	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
