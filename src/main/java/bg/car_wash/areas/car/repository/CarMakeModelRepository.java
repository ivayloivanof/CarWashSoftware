package bg.car_wash.areas.car.repository;

import bg.car_wash.areas.car.entity.CarMakeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMakeModelRepository extends JpaRepository<CarMakeModel, Long> {

}
