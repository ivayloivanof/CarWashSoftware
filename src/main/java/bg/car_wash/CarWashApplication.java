package bg.car_wash;

import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.services.UserService;
import bg.car_wash.configurations.database.UserDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.List;

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

}
