package bg.car_wash.areas.car.repositories;

import bg.car_wash.areas.car.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	Car findCarByCarRegistrationNumber(String registrationNUmber);

	List<Car> findCarsByCarRegistrationNumberStartsWithOrderByCarRegistrationNumber(String carRegistrationNumber);

	@Transactional
	void deleteCarById(Long id);

	@Transactional
	Car findCarById(Long id);
}
