package bg.car_wash.areas.car.servicesImpl;

import bg.car_wash.areas.car.entities.Car;
import bg.car_wash.areas.car.exceptions.CarDBEmptyException;
import bg.car_wash.areas.car.exceptions.CarNotCreateException;
import bg.car_wash.areas.car.exceptions.CarNotFoundException;
import bg.car_wash.areas.car.models.bindingModel.CarBindingModel;
import bg.car_wash.areas.car.models.viewModel.CarViewModel;
import bg.car_wash.areas.car.repositories.CarRepository;
import bg.car_wash.areas.car.services.CarService;
import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.models.viewModels.CustomerViewModel;
import bg.car_wash.areas.customer.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

	private CarRepository carRepository;
	private ModelMapper modelMapper;
	private CustomerService customerService;

	@Autowired
	public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, CustomerService customerService) {
		this.carRepository = carRepository;
		this.modelMapper = modelMapper;
		this.customerService = customerService;
	}

	@Override
	public void createCar(CarBindingModel carBindingModel) throws CarNotCreateException {
		if (carBindingModel == null) {
			throw new CarNotCreateException("Car not create when Car object is null!");
		}

		Customer customer = this.customerService.findCustomerById(carBindingModel.getCustomerId());
		Car car = this.modelMapper.map(carBindingModel, Car.class);

		car.setCarModelName(carBindingModel.getCarModel());
		car.setCarRegistrationNumber(car.getCarRegistrationNumber().toUpperCase());
		car.setOwner(customer);

		this.carRepository.save(car);
	}

	@Override
	public void updateCar(CarBindingModel carBindingModel) throws CarNotFoundException {

		Customer customer;
		if (carBindingModel.getCustomerId() != null) {
			customer = this.customerService.findCustomerById(carBindingModel.getCustomerId());
		} else {
			customer = this.customerService.findCustomerById(1L);
		}

		carBindingModel.setOwner(this.modelMapper.map(customer, CustomerViewModel.class));

		Car car = this.modelMapper.map(carBindingModel, Car.class);
		car.setCarModelName(carBindingModel.getCarModel());
		car.setCarRegistrationNumber(car.getCarRegistrationNumber().toUpperCase());

		this.carRepository.save(car);
	}

	@Override
	public List<CarViewModel> findAllCars() {
		List<Car> cars = this.carRepository.findAll();
		if (cars.isEmpty()) {
			throw new CarDBEmptyException("Car database is empty!");
		}

		List<CarViewModel> carsViewModel = new LinkedList<>();
		for (Car car : cars) {
			carsViewModel.add(this.modelMapper.map(car, CarViewModel.class));
		}

		return carsViewModel;
	}

	@Override
	public void deleteCarById(Long id) throws CarNotFoundException {
		if (id == null || id < 1) {
			throw new CarNotFoundException("Car not found in database!");
		}

		this.carRepository.deleteCarById(id);
	}

	@Override
	public CarViewModel findCarById(Long id) throws CarNotFoundException {
		Car car = this.carRepository.findCarById(id);
		if (car == null) {
			throw new CarNotFoundException("Car not found in database!");
		}

		CarViewModel carViewModel = this.modelMapper.map(car, CarViewModel.class);

		return carViewModel;
	}

	@Override
	public List<CarViewModel> findCarByRegistrationNumber(String carRegistrationNumber) {
		List<Car> cars = this.carRepository.findCarsByCarRegistrationNumberStartsWithOrderByCarRegistrationNumber(carRegistrationNumber);
		if (cars.isEmpty()) {
			throw new CarNotFoundException("Car not found in database!");
		}

		List<CarViewModel> carsViewModel = new LinkedList<>();
		for (Car car : cars) {
			carsViewModel.add(this.modelMapper.map(car, CarViewModel.class));
		}

		return carsViewModel;
	}

	public List<CustomerViewModel> getAllCustomers() {
		List<Customer> customers = this.customerService.findAllCustomers();
		List<CustomerViewModel> customerViewModels = new LinkedList<>();
		for (Customer customer : customers) {
			customerViewModels.add(this.modelMapper.map(customer, CustomerViewModel.class));
		}

		return customerViewModels;
	}
}
