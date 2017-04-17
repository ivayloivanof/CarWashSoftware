package bg.car_wash;

import bg.car_wash.areas.car.entity.CarMakeModel;
import bg.car_wash.areas.car.service.CarMakeModelService;
import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.service.UserService;
import bg.car_wash.configurations.database.InsertCars;
import bg.car_wash.configurations.database.InsertUsers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.util.List;

@SpringBootApplication
public class CarWashApplication {

	@Autowired
	private UserService userService;

	@Autowired
	private CarMakeModelService carMakeModelService;

	public static void main(String[] args) {
		SpringApplication.run(CarWashApplication.class, args);
	}

	@PostConstruct
	public void initUsers() throws FileNotFoundException {
		//create 6 default users in DB
		if (checkUserDbIsEmpty()) {
			InsertUsers userDefault = new InsertUsers();
			for (User user : userDefault.getUsers()) {
				this.userService.createUser(user);
			}
		}

		//create all cars with models and makes in DB
		if(checkCarMakeModelDbIsEmpty()) {
			InsertCars insertCars = new InsertCars();
			List<CarMakeModel> carMakeModels = insertCars.readJsonFile();
			for (CarMakeModel carMakeModel : carMakeModels) {
				this.carMakeModelService.createCar(carMakeModel);
			}
		}
	}

	private boolean checkCarMakeModelDbIsEmpty() {
		if(this.carMakeModelService.findAllCarMakeModel().isEmpty()) {
			return true;
		}

		return false;
	}

	private boolean checkUserDbIsEmpty() {
		if (this.userService.findAllUsers().isEmpty()) {
			return true;
		}

		return false;
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
