package bg.car_wash.areas.service.repository;

import bg.car_wash.areas.service.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
	Service findServiceById(Long id);
	Service findServiceByServiceName(String name);
}
