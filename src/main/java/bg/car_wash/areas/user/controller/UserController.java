package bg.car_wash.areas.user.controller;

import bg.car_wash.areas.user.exception.UserNotFoundException;
import bg.car_wash.configurations.error.Errors;
import bg.car_wash.configurations.site.PageTitle;
import bg.car_wash.configurations.user.UserConfiguration;
import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.entity.UserType;
import bg.car_wash.areas.user.models.bindingModels.UserLoginBindingModel;
import bg.car_wash.areas.user.models.bindingModels.UserRegisterBindingModel;
import bg.car_wash.areas.user.services.UserService;
import bg.car_wash.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private ModelParser modelParser;

	@GetMapping("/status")
	public String getUserStatusPage(HttpServletRequest httpServletRequest, Model model) {
		model.addAttribute("pageTitle", PageTitle.USER_STATUS_PAGE);

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
	public String getLoginPage(
			Model model,
			@ModelAttribute UserLoginBindingModel userLoginBindingModel,
			@RequestParam(required = false) String error) {
		model.addAttribute("pageTitle", PageTitle.LOGIN_PAGE);

		if(error != null) {
			model.addAttribute("error", Errors.INVALID_CREDENTIALS);
		}

		return "user/user-login";
	}

//	@PostMapping("/login")
//	public String loginUser(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
//							BindingResult bindingResult,
//							HttpServletResponse httpServletResponse) {
//		if(bindingResult.hasErrors()) {
//			return "user/user-login";
//		}
//
//		UserSessionViewModel user = this.userService.getUserByFullNameAndPassword(userLoginBindingModel);
//		if(user == null) {
//			return "user/user-login";
//		}
//
//		UserCreateCookie userCreateCookie = new UserCreateCookie(user);
//
//		for (Cookie cookie : userCreateCookie.getCookies()) {
//			httpServletResponse.addCookie(cookie);
//		}
//
//		return "redirect:/user/status";
//	}

	@GetMapping("/register")
	public String getRegisterPage(Model model, @ModelAttribute UserRegisterBindingModel userRegisterBindingModel) {
		model.addAttribute("pageTitle", PageTitle.REGISTER_PAGE);
		return "user/user-register";
	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "user/user-register";
		}

		User user = this.modelParser.convert(userRegisterBindingModel, User.class);
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserType(UserType.WORKER);
		user.setRemuneration(new BigDecimal(UserConfiguration.WORKER_REMUNERATION));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);

		this.userService.createUser(user);

		return "redirect:/user/login";
	}

	@GetMapping("/logout")
	public String logoutUser(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		Cookie[] cookies = httpServletRequest.getCookies();

		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
			httpServletResponse.addCookie(cookie);
		}

		return "redirect:/user/login";
	}

	@ExceptionHandler(UserNotFoundException.class)
	public String catchUserNotFoundException() {
		//TODO create and return error page from not found user
		return "";
	}

}
