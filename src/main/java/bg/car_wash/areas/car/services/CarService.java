package bg.car_wash.areas.car.services;

import bg.car_wash.areas.car.exceptions.CarDBEmptyException;
import bg.car_wash.areas.car.exceptions.CarNotCreateException;
import bg.car_wash.areas.car.exceptions.CarNotFoundException;
import bg.car_wash.areas.car.models.bindingModel.CarBindingModel;
import bg.car_wash.areas.car.models.viewModel.CarViewModel;
import bg.car_wash.areas.customer.models.viewModels.CustomerViewModel;

import java.util.List;

public interface CarService {

	void createCar(CarBindingModel car) throws CarNotCreateException;

	void updateCar(CarBindingModel car) throws CarNotFoundException;

	List<CarViewModel> findAllCars() throws CarDBEmptyException;

	void deleteCarById(Long id) throws CarNotFoundException;

	CarViewModel findCarById(Long id) throws CarNotFoundException;

	List<CarViewModel> findCarByRegistrationNumber(String carRegistrationNumber);

	List<CustomerViewModel> getAllCustomers();
}
