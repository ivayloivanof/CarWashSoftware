package bg.car_wash.areas.service.serviceImpl;

import bg.car_wash.areas.service.entity.Service;
import bg.car_wash.areas.service.exceptions.ServiceDBEmptyException;
import bg.car_wash.areas.service.exceptions.ServiceNotCreateException;
import bg.car_wash.areas.service.exceptions.ServiceNotFoundException;
import bg.car_wash.areas.service.exceptions.ServiceNotUpdateException;
import bg.car_wash.areas.service.repository.ServiceRepository;
import bg.car_wash.areas.service.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

	private ServiceRepository serviceRepository;

	@Autowired
	public ServiceServiceImpl(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	@Override
	public void createService(Service service) throws ServiceNotCreateException {
		if (service == null) {
			throw new ServiceNotCreateException("Service can not create in database!");
		}

		this.serviceRepository.save(service);
	}

	@Override
	public List<Service> findAllServices() throws ServiceDBEmptyException {
		List<Service> services = this.serviceRepository.findAll();
		if (services.isEmpty()) {
			throw new ServiceDBEmptyException("Service database is empty");
		}

		return services;
	}

	@Override
	public Service findServiceById(Long id) throws ServiceNotFoundException {
		Service service = this.serviceRepository.findServiceById(id);
		if (service == null) {
			throw new ServiceNotFoundException("This service is not found in database");
		}

		return service;
	}

	@Override
	public Service findServiceByName(String name) throws ServiceNotFoundException {
		Service service = this.serviceRepository.findServiceByServiceName(name);
		if (service == null) {
			throw new ServiceNotFoundException("This service is not found in database");
		}

		return service;
	}

	@Override
	public void updateService(Service service) throws ServiceNotUpdateException {
		if (service == null) {
			throw new ServiceNotUpdateException("This service can not update!");
		}

		this.serviceRepository.save(service);
	}

	@Override
	public void deleteServiceById(Long id) throws ServiceNotFoundException {
		if (id == null || id < 1) {
			throw new ServiceNotFoundException("This service can not delete!");
		}

		this.serviceRepository.delete(id);
	}
}
