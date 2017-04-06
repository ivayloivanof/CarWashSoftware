package bg.car_wash.areas.car.repository;

import bg.car_wash.areas.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

	Car findByCarRegistrationNumber(String registrationNUmber);
}
