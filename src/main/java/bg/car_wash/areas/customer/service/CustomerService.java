package bg.car_wash.areas.customer.service;

import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.exceptions.CustomerDBEmptyException;
import bg.car_wash.areas.customer.exceptions.CustomerNotCreateException;
import bg.car_wash.areas.customer.exceptions.CustomerNotFoundException;
import bg.car_wash.areas.customer.exceptions.CustomerNotUpdateException;
import bg.car_wash.areas.customer.models.bindingModel.CustomerBindingModel;
import bg.car_wash.areas.customer.models.viewModels.CustomerViewModel;
import bg.car_wash.areas.customer.models.viewModels.CustomerWithCarsViewModel;

import java.util.List;

public interface CustomerService {
	void createCustomer(CustomerBindingModel customerBindingModel) throws CustomerNotCreateException;

	void updateCustomer(CustomerBindingModel customerBindingModel) throws CustomerNotUpdateException;

	void deleteCustomerById(Long id) throws CustomerNotFoundException;

	List<CustomerViewModel> findAllCustomers() throws CustomerDBEmptyException;

	CustomerViewModel findCustomerByName(String name) throws CustomerNotFoundException;

	CustomerWithCarsViewModel findCustomerById(Long id) throws CustomerNotFoundException;

	CustomerViewModel getCustomerViewModel(Long id);

	List<CustomerWithCarsViewModel> findAllCustomersWithCars();

	CustomerWithCarsViewModel findCustomerWithCars(Long id);

	Customer findCustomerEntityById(Long customerId);
}
