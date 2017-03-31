package bg.car_wash.areas.user.userController;

import bg.car_wash.areas.user.exception.UserNotFoundException;
import bg.car_wash.configurations.SiteTitleNames;
import bg.car_wash.configurations.UserConfiguration;
import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.entity.UserType;
import bg.car_wash.areas.user.models.bindingModels.user.UserLoginBindingModel;
import bg.car_wash.areas.user.models.bindingModels.user.UserRegisterBindingModel;
import bg.car_wash.areas.user.models.viewModels.user.UserSessionViewModel;
import bg.car_wash.areas.user.services.UserService;
import bg.car_wash.utils.parser.interfaces.ModelParser;
import bg.car_wash.utils.user.UserCreateCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelParser modelParser;

	@GetMapping("/status")
	public String getUserStatusPage(HttpServletRequest httpServletRequest, Model model) {
		model.addAttribute("pageTitle", SiteTitleNames.USER_STATUS_PAGE);

		Cookie[] cookies = httpServletRequest.getCookies();
		for (Cookie cookie : cookies) {
			model.addAttribute(cookie.getName(), cookie.getValue());
		}

		//TODO check for user is active
		if(cookies.length > 0) {
			return "user/user-status";
		}

		return "redirect:/user/login";
	}

	@GetMapping("/login")
	public String getLoginPage(Model model, @ModelAttribute UserLoginBindingModel userLoginBindingModel) {
		model.addAttribute("pageTitle", SiteTitleNames.LOGIN_PAGE);
		return "user/user-login";
	}

	@PostMapping("/login")
	public String loginUser(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
							BindingResult bindingResult,
							HttpServletResponse httpServletResponse) {
		if(bindingResult.hasErrors()) {
			return "user/user-login";
		}

		UserSessionViewModel user = this.userService.getUserByFullNameAndPassword(userLoginBindingModel);
		if(user == null) {
			return "user/user-login";
		}

		UserCreateCookie userCreateCookie = new UserCreateCookie(user);

		for (Cookie cookie : userCreateCookie.getCookies()) {
			httpServletResponse.addCookie(cookie);
		}

		return "redirect:/user/status";
	}

	@GetMapping("/register")
	public String getRegisterPage(Model model, @ModelAttribute UserRegisterBindingModel userRegisterBindingModel) {
		model.addAttribute("pageTitle", SiteTitleNames.REGISTER_PAGE);
		return "user/user-register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "user/user-register";
		}

		User user = this.modelParser.convert(userRegisterBindingModel, User.class);
		List<User> usersFromDb = this.userService.findAllUsers();

		if(usersFromDb.isEmpty()) {
			user.setUserType(UserType.DIRECTOR);
			user.setRemuneration(new BigDecimal(UserConfiguration.DIRECTOR_REMUNERATION));
		} else if (usersFromDb.size() == 1) {
			user.setUserType(UserType.ADMIN);
			user.setRemuneration(new BigDecimal(UserConfiguration.ADMIN_REMUNERATION));
		} else {
			user.setUserType(UserType.WORKER);
			user.setRemuneration(new BigDecimal(UserConfiguration.WORKER_REMUNERATION));
		}

		this.userService.createUser(user);

		return "redirect:/user/login";
	}

	@GetMapping("/logout")
	public String logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies.length > 1) {
			for (Cookie cookie : cookies) {
				cookie.setMaxAge(0);
				httpServletResponse.addCookie(cookie);
			}
		}

		return "redirect:/";
	}

	@ExceptionHandler(UserNotFoundException.class)
	public String catchUserNotFoundException() {

		//TODO create and return error page from not found user
		return "";
	}

}
