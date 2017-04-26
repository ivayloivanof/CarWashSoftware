package bg.car_wash.areas.customer.service;

import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.exceptions.CustomerDBEmptyException;
import bg.car_wash.areas.customer.exceptions.CustomerNotCreateException;
import bg.car_wash.areas.customer.exceptions.CustomerNotFoundException;
import bg.car_wash.areas.customer.exceptions.CustomerNotUpdateException;

import java.util.List;

public interface CustomerService {
	void createCustomer(Customer customer) throws CustomerNotCreateException;

	void updateCustomer(Customer customer) throws CustomerNotUpdateException;

	void deleteCustomerById(Long id) throws CustomerNotFoundException;

	List<Customer> findAllCustomers() throws CustomerDBEmptyException;

	Customer findCustomerByName(String name) throws CustomerNotFoundException;

	Customer findCustomerById(Long id) throws CustomerNotFoundException;
}
