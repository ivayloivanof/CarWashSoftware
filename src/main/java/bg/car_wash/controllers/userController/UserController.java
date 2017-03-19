package bg.car_wash.controllers.userController;

import bg.car_wash.configurations.SiteTitleNames;
import bg.car_wash.configurations.UserConfiguration;
import bg.car_wash.entities.User;
import bg.car_wash.entities.enumerations.UserType;
import bg.car_wash.models.bindingModels.user.UserLoginBindingModel;
import bg.car_wash.models.bindingModels.user.UserRegisterBindingModel;
import bg.car_wash.models.viewModels.user.UserSessionViewModel;
import bg.car_wash.services.UserService;
import bg.car_wash.utils.parser.interfaces.ModelParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
	public String getUserStatusPage(HttpSession httpSession, HttpServletRequest httpServletRequest) {
		if(httpSession.getAttribute("username") != null) {
			return "user-status";
		}

		Cookie[] cookies = httpServletRequest.getCookies();

		return "redirect:/login";
	}

	@GetMapping("/login")
	public String getLoginPage(Model model, @ModelAttribute UserLoginBindingModel userLoginBindingModel) {
		model.addAttribute("pageTitle", SiteTitleNames.LOGIN_PAGE);
		return "user/user-login";
	}

	@PostMapping("/login")
	public String loginUser(@Valid @ModelAttribute UserLoginBindingModel userLoginBindingModel,
							BindingResult bindingResult,
							HttpSession httpSession,
							HttpServletResponse httpServletResponse) {
		if(bindingResult.hasErrors()) {
			return "user/user-login";
		}

		UserSessionViewModel user = this.userService.getUserByFullNameAndPassword(userLoginBindingModel);
		if(user == null) {
			return "user/user-login";
		}

		httpSession.setAttribute("user-email", user.getEmail());
		httpSession.setAttribute("user-full-name", user.getFullName());
		httpServletResponse.addCookie(new Cookie("user", user.getFullName()));

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
}
