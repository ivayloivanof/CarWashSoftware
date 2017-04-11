package bg.car_wash.areas.customer.serviceImpl;

import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.exception.CustomerDBEmptyException;
import bg.car_wash.areas.customer.exception.CustomerNotCreateException;
import bg.car_wash.areas.customer.exception.CustomerNotFoundException;
import bg.car_wash.areas.customer.repository.CustomerRepository;
import bg.car_wash.areas.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
		this.customerRepository.save(customer);
	}

	@Override
	public List<Customer> findAllCustomers() throws CustomerDBEmptyException {
		List<Customer> customers = this.customerRepository.findAll();
		if (customers.isEmpty()) {
			throw new CustomerDBEmptyException("Not found users in DB", 404);
		}

		return customers;
	}

	@Override
	public Customer findCustomerByName(String name) throws CustomerNotFoundException {
		Customer customer = this.customerRepository.findCustomerByName(name.trim());
		if (customer == null) {
			throw new CustomerNotFoundException("This user is not found in DB", 404);
		}

		return customer;
	}

	@Override
	public Customer findCustomerById(Long id) throws CustomerNotFoundException {
		Customer customer = this.customerRepository.findCustomerById(id);
		if (customer == null) {
			throw new CustomerNotFoundException(String.format("Customer with id:%d - not found!", id), 404);
		}

		return customer;
	}
}
