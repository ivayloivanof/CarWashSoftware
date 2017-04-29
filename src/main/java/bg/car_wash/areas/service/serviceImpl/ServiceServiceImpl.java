package bg.car_wash.areas.service.serviceImpl;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.repository.ActivityRepository;
import bg.car_wash.areas.car.entities.Car;
import bg.car_wash.areas.car.repositories.CarRepository;
import bg.car_wash.areas.service.entity.Service;
import bg.car_wash.areas.service.exceptions.ServiceDBEmptyException;
import bg.car_wash.areas.service.exceptions.ServiceNotCreateException;
import bg.car_wash.areas.service.exceptions.ServiceNotFoundException;
import bg.car_wash.areas.service.exceptions.ServiceNotUpdateException;
import bg.car_wash.areas.service.models.bindingModel.ServiceBindingModel;
import bg.car_wash.areas.service.models.viewModel.ServiceViewModel;
import bg.car_wash.areas.service.repository.ServiceRepository;
import bg.car_wash.areas.service.service.ServiceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

	private ServiceRepository serviceRepository;
	private ActivityRepository activityRepository;
	private CarRepository carRepository;
	private ModelMapper modelMapper;

	@Autowired
	public ServiceServiceImpl(ServiceRepository serviceRepository, ActivityRepository activityRepository, CarRepository carRepository, ModelMapper modelMapper) {
		this.serviceRepository = serviceRepository;
		this.activityRepository = activityRepository;
		this.modelMapper = modelMapper;
		this.carRepository = carRepository;
	}

	@Override
	public void createService(ServiceBindingModel serviceBindingModel) throws ServiceNotCreateException {
		if (serviceBindingModel == null) {
			throw new ServiceNotCreateException("Service can not create in database!");
		}

		Service service = this.modelMapper.map(serviceBindingModel, Service.class);

		Activity activity = this.activityRepository.findActivityById(serviceBindingModel.getActivityId());
		List<Activity> activities = new LinkedList<>();
		activities.add(activity);
		service.setActivities(activities);

		Car car = this.carRepository.findCarById(serviceBindingModel.getCarId());
		List<Car> cars = new LinkedList<>();
		cars.add(car);
		service.setCars(cars);

		service.setCarType(car.getCarType());

		this.serviceRepository.save(service);
	}

	@Override
	public List<ServiceViewModel> findAllServices() throws ServiceDBEmptyException {
		List<Service> services = this.serviceRepository.findAll();
		if (services.isEmpty()) {
			throw new ServiceDBEmptyException("Service database is empty");
		}
		List<ServiceViewModel> servicesViewModel = new LinkedList<>();
		for (Service service : services) {
			servicesViewModel.add(this.modelMapper.map(service, ServiceViewModel.class));
		}

		return servicesViewModel;
	}

	@Override
	public ServiceViewModel findServiceById(Long id) throws ServiceNotFoundException {
		Service service = this.serviceRepository.findServiceById(id);
		if (service == null) {
			throw new ServiceNotFoundException("This service is not found in database");
		}

		ServiceViewModel serviceViewModel = this.modelMapper.map(service, ServiceViewModel.class);

		return serviceViewModel;
	}

	@Override
	public ServiceViewModel findServiceByName(String name) throws ServiceNotFoundException {
		Service service = this.serviceRepository.findServiceByServiceName(name);
		if (service == null) {
			throw new ServiceNotFoundException("This service is not found in database");
		}
		ServiceViewModel serviceViewModel = this.modelMapper.map(service, ServiceViewModel.class);

		return serviceViewModel;
	}

	@Override
	public void updateService(ServiceBindingModel serviceBindingModel) throws ServiceNotUpdateException {
		if (serviceBindingModel == null) {
			throw new ServiceNotUpdateException("This service can not update!");
		}

		Service service = this.modelMapper.map(serviceBindingModel, Service.class);

		this.serviceRepository.save(service);
	}

	@Override
	public void deleteServiceById(Long id) throws ServiceNotFoundException {
		if (id == null || id < 1) {
			throw new ServiceNotFoundException("This service can not delete!");
		}

		this.serviceRepository.delete(id);
	}

	public ServiceViewModel getServiceViewModel(Long id) {
		Service service = this.serviceRepository.findServiceById(id);
		ServiceViewModel serviceViewModel = this.modelMapper.map(service, ServiceViewModel.class);

		return serviceViewModel;
	}
}
