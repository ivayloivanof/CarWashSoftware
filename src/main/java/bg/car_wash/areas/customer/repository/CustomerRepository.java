package bg.car_wash.areas.customer.repository;

import bg.car_wash.areas.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findCustomerById(Long id);
	Customer findCustomerByName(String name);
	Customer findCustomerByPhoneNumber(String phone);
}
