package bg.car_wash.areas.customer.serviceImpl;

import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.exceptions.CustomerDBEmptyException;
import bg.car_wash.areas.customer.exceptions.CustomerNotCreateException;
import bg.car_wash.areas.customer.exceptions.CustomerNotFoundException;
import bg.car_wash.areas.customer.exceptions.CustomerNotUpdateException;
import bg.car_wash.areas.customer.models.bindingModel.CustomerBindingModel;
import bg.car_wash.areas.customer.models.viewModels.CustomerViewModel;
import bg.car_wash.areas.customer.models.viewModels.CustomerWithCarsViewModel;
import bg.car_wash.areas.customer.repository.CustomerRepository;
import bg.car_wash.areas.customer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	private ModelMapper modelMapper;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void createCustomer(CustomerBindingModel customerBindingModel) throws CustomerNotCreateException {
		if (customerBindingModel == null) {
			throw new CustomerNotCreateException("Customer can not create in database!");
		}

		Customer customer = this.modelMapper.map(customerBindingModel, Customer.class);

		this.customerRepository.save(customer);
	}

	@Override
	public void updateCustomer(CustomerBindingModel customerBindingModel) throws CustomerNotUpdateException {
		if (customerBindingModel == null) {
			throw new CustomerNotUpdateException("Customer can not update!");
		}

		Customer customer = this.modelMapper.map(customerBindingModel, Customer.class);
		customer.setDate(new Date());

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
	public List<CustomerViewModel> findAllCustomers() throws CustomerDBEmptyException {
		List<Customer> customers = this.customerRepository.findAll(new Sort(Sort.Direction.ASC, "name", "phoneNumber"));
		if (customers.isEmpty()) {
			throw new CustomerDBEmptyException("Not found users in database");
		}

		List<CustomerViewModel> customersViewModel = new LinkedList<>();
		for (Customer customer : customers) {
			customersViewModel.add(this.modelMapper.map(customer, CustomerViewModel.class));
		}

		return customersViewModel;
	}

	@Override
	public CustomerViewModel findCustomerByName(String name) throws CustomerNotFoundException {
		Customer customer = this.customerRepository.findCustomerByName(name.trim());
		if (customer == null) {
			throw new CustomerNotFoundException("This user is not found in Database");
		}

		CustomerViewModel customerViewModel = this.modelMapper.map(customer, CustomerViewModel.class);

		return customerViewModel;
	}

	@Override
	public CustomerWithCarsViewModel findCustomerById(Long id) throws CustomerNotFoundException {
		Customer customer = this.customerRepository.findCustomerById(id);
		if (customer == null) {
			throw new CustomerNotFoundException(String.format("Customer with id: %d - not found!", id));
		}

		CustomerWithCarsViewModel customerViewModel = this.modelMapper.map(customer, CustomerWithCarsViewModel.class);

		return customerViewModel;
	}

	public CustomerViewModel getCustomerViewModel(Long id) {
		Customer customer = this.customerRepository.findCustomerById(id);
		CustomerViewModel customerViewModel = this.modelMapper.map(customer, CustomerViewModel.class);

		return customerViewModel;
	}

	@Override
	public List<CustomerWithCarsViewModel> findAllCustomersWithCars() {
		List<Customer> customers = this.customerRepository.findAll(new Sort(Sort.Direction.ASC, "name", "phoneNumber"));
		if (customers.isEmpty()) {
			throw new CustomerDBEmptyException("Not found users in database");
		}

		List<CustomerWithCarsViewModel> customersViewModel = new LinkedList<>();
		for (Customer customer : customers) {
			customersViewModel.add(this.modelMapper.map(customer, CustomerWithCarsViewModel.class));
		}

		return customersViewModel;
	}

	@Override
	public CustomerWithCarsViewModel findCustomerWithCars(Long id) {
		Customer customer = this.customerRepository.findCustomerById(id);
		CustomerWithCarsViewModel customerViewModel = this.modelMapper.map(customer, CustomerWithCarsViewModel.class);

		return customerViewModel;
	}

	@Override
	public Customer findCustomerEntityById(Long customerId) {
		Customer customer = this.customerRepository.findCustomerById(customerId);

		return customer;
	}
}
