package bg.car_wash.areas.car.servicesImpl;

import bg.car_wash.areas.car.entities.Car;
import bg.car_wash.areas.car.entities.CarType;
import bg.car_wash.areas.car.exceptions.CarNotFoundException;
import bg.car_wash.areas.car.models.viewModel.CarViewModel;
import bg.car_wash.areas.car.repositories.CarRepository;
import bg.car_wash.areas.car.services.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CarServiceTest {

	public static final Long VALID_ID = 1L;
	public static final Long INVALID_ID = -1L;
	public static final String CAR_MAKE = "Opel";
	public static final String CAR_MODEL = "Corsa";
	public static final String CAR_REGISTRATION_NUMBER = "CA4554HT";

	@MockBean
	private CarRepository carRepository;

	@Autowired
	private CarService carService;

	@Before
	public void setUp() throws Exception {
		Car car = new Car();
		car.setId(VALID_ID);
		car.setCarMake(CAR_MAKE);
		car.setCarModelName(CAR_MODEL);
		car.setCarRegistrationNumber(CAR_REGISTRATION_NUMBER);
		car.setCarType(CarType.CAR);

		Mockito.when(carRepository.findCarById(VALID_ID)).thenReturn(car);
	}

	@Test
	public void findCarByIdGivenValidCar_ShouldReturnValidCarId() throws Exception {
		//Act
		CarViewModel carViewModel = this.carService.findCarById(VALID_ID);

		//Assert
		assertEquals(VALID_ID, carViewModel.getId());
	}

	@Test
	public void findCarByIdGivenValidCar_ShouldReturnValidCarMake() throws Exception {
		//Act
		CarViewModel carViewModel = this.carService.findCarById(VALID_ID);

		//Assert
		assertEquals(CAR_MAKE, carViewModel.getCarMake());
	}

	@Test
	public void findCarByIdGivenValidCar_ShouldReturnValidCarModel() throws Exception {
		//Act
		CarViewModel carViewModel = this.carService.findCarById(VALID_ID);

		//Assert
		assertEquals(CAR_MODEL, carViewModel.getCarModel());
	}

	@Test
	public void findCarByIdGivenValidCar_ShouldReturnValidCarRegistrationNumber() throws Exception {
		//Act
		CarViewModel carViewModel = this.carService.findCarById(VALID_ID);

		//Assert
		assertEquals(CAR_REGISTRATION_NUMBER, carViewModel.getCarRegistrationNumber());
	}

	@Test
	public void findCarByIdGivenValidCar_ShouldOneCallFindCarById() throws Exception {
		//Act
		CarViewModel carViewModel = this.carService.findCarById(VALID_ID);

		//Assert
		Mockito.verify(this.carRepository, Mockito.times(1)).findCarById(VALID_ID);
	}

	@Test(expected = CarNotFoundException.class)
	public void findCarByIdGivenInvalidCar_ShouldThrowException() throws Exception {
		//Act
		CarViewModel carViewModel = this.carService.findCarById(INVALID_ID);
	}
}