package bg.car_wash.areas.car.repository;

import bg.car_wash.areas.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	Car findCarByCarRegistrationNumber(String registrationNUmber);

	@Transactional
	void deleteCarById(Long id);

	@Transactional
	Car findCarById(Long id);
}
