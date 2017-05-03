package bg.car_wash.configurations;

import bg.car_wash.areas.user.interceptors.CreateCookieInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class UserCreateCookieConfig extends WebMvcConfigurerAdapter {

	private CreateCookieInterceptor createCookieInterceptor;

	@Autowired
	public UserCreateCookieConfig(CreateCookieInterceptor createCookieInterceptor) {
		this.createCookieInterceptor = createCookieInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.createCookieInterceptor);
	}
}
