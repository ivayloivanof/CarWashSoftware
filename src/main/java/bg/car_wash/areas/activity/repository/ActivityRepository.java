package bg.car_wash.areas.activity.repository;

import bg.car_wash.areas.activity.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
	Activity findByActivityName(String name);
}
