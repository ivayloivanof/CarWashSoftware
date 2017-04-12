package bg.car_wash;

import bg.car_wash.areas.user.service.UserService;
import bg.car_wash.configurations.database.InsertCars;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;

@SpringBootApplication
public class CarWashApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(CarWashApplication.class, args);
	}

//	@PostConstruct
//	public void initUsers() throws FileNotFoundException {
////		InsertUsers userDefault = new InsertUsers();
////		for (User user : userDefault.getUsers()) {
////			this.userService.createUser(user);
////		}
//
//		InsertCars insertCars = new InsertCars();
//	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
