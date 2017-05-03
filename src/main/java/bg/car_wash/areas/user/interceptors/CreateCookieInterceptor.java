package bg.car_wash.areas.user.interceptors;

import bg.car_wash.areas.user.cookies.UserCookie;
import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.models.viewModels.UserViewModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CreateCookieInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser")) {
			User user = (User) auth.getPrincipal();

			UserViewModel userViewModel = new UserViewModel(user.getEmail(), user.getFullName().replace(" ", "_"), user.getUserType());
			UserCookie userCookie = new UserCookie(userViewModel);

			for (Cookie cookie : userCookie.getCookies()) {
				response.addCookie(cookie);
			}
		}

		return true;
	}
}
