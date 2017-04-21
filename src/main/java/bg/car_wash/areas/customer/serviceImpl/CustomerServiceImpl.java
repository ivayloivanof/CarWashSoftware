package bg.car_wash.areas.customer.serviceImpl;

import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.exception.CustomerDBEmptyException;
import bg.car_wash.areas.customer.exception.CustomerNotCreateException;
import bg.car_wash.areas.customer.exception.CustomerNotFoundException;
import bg.car_wash.areas.customer.exception.CustomerNotUpdateException;
import bg.car_wash.areas.customer.repository.CustomerRepository;
import bg.car_wash.areas.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public void createCustomer(Customer customer) throws CustomerNotCreateException {
		if (customer == null) {
			throw new CustomerNotCreateException("Customer can not create in database!");
		}
		this.customerRepository.save(customer);
	}

	@Override
	public void updateCustomer(Customer customer) throws CustomerNotUpdateException {
		if (customer == null) {
			throw new CustomerNotUpdateException("Customer can not update!");
		}

		this.customerRepository.save(customer);
	}

	@Override
	public void deleteCustomerById(Long id) throws CustomerNotFoundException {
		if (id == null || id < 1) {
			throw new CustomerNotFoundException("Customer can not delete from database!");
		}

		this.customerRepository.deleteCustomerById(id);
	}

	@Override
	public List<Customer> findAllCustomers() throws CustomerDBEmptyException {
		List<Customer> customers = this.customerRepository.findAll(new Sort(Sort.Direction.ASC, "name", "phoneNumber"));
		if (customers.isEmpty()) {
			throw new CustomerDBEmptyException("Not found users in database");
		}

		return customers;
	}

	@Override
	public Customer findCustomerByName(String name) throws CustomerNotFoundException {
		Customer customer = this.customerRepository.findCustomerByName(name.trim());
		if (customer == null) {
			throw new CustomerNotFoundException("This user is not found in Database");
		}

		return customer;
	}

	@Override
	public Customer findCustomerById(Long id) throws CustomerNotFoundException {
		Customer customer = this.customerRepository.findCustomerById(id);
		if (customer == null) {
			throw new CustomerNotFoundException(String.format("Customer with id: %d - not found!", id));
		}

		return customer;
	}
}
