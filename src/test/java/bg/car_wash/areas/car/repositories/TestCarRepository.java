package bg.car_wash.areas.car.repositories;

import bg.car_wash.areas.car.entities.Car;
import bg.car_wash.areas.car.entities.CarType;
import bg.car_wash.exception.CarWashErrorException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class TestCarRepository {

	public static final int CAR_LIST_SIZE = 5;

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private CarRepository carRepository;

	@Before
	public void setUp() throws Exception {
		Car car = new Car();
		car.setCarMake("Opel");
		car.setCarModelName("Corsa");
		car.setCarRegistrationNumber("CA4554HT");
		car.setCarType(CarType.CAR);

		//Arrange
		for (int i = 0; i < CAR_LIST_SIZE; i++) {
			this.testEntityManager.persist(car);
		}
	}

	@Test
	public void testFindAllCarsAndReturnCorrectSize() throws Exception {
		//Act
		List<Car> cars = this.carRepository.findAll();

		//Assert
		assertEquals(CAR_LIST_SIZE, cars.size());
	}
}
