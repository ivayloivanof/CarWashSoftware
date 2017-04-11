package bg.car_wash.areas.customer.service;

import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.exception.CustomerDBEmptyException;
import bg.car_wash.areas.customer.exception.CustomerNotCreateException;
import bg.car_wash.areas.customer.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerService {
	void createCustomer(Customer customer) throws CustomerNotCreateException;

	List<Customer> findAllCustomers() throws CustomerDBEmptyException;

	Customer findCustomerByName(String name) throws CustomerNotFoundException;

	Customer findCustomerById(Long id) throws CustomerNotFoundException;
}
