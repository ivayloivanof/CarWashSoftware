package bg.car_wash.areas.user.controllers;

import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.models.viewModels.UserViewModel;
import bg.car_wash.areas.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/json/user")
public class UserJsonController {

	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private ModelMapper modelMapper;

	@Autowired
	public UserJsonController(
			UserService userService,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			ModelMapper modelMapper) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.modelMapper = modelMapper;
	}

	@GetMapping("/status")
	public ResponseEntity getUserStatus() {

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserViewModel userViewModel = this.modelMapper.map(user, UserViewModel.class);

		if (userViewModel == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}

		ResponseEntity<UserViewModel> responseEntityCar = new ResponseEntity(userViewModel, HttpStatus.OK);
		return responseEntityCar;
	}
}
