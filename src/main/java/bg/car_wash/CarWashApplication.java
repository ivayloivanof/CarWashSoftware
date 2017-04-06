package bg.car_wash;

import bg.car_wash.areas.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CarWashApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(CarWashApplication.class, args);
	}

//	@PostConstruct
//	public void initUsers() {
//		UserDefault userDefault = new UserDefault();
//		for (User user : userDefault.getUsers()) {
//			this.userService.createUser(user);
//		}
//	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
