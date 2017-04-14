package bg.car_wash.areas.service.serviceImpl;

import bg.car_wash.areas.service.entity.Service;
import bg.car_wash.areas.service.exception.ServiceDBEmptyException;
import bg.car_wash.areas.service.exception.ServiceNotCreateException;
import bg.car_wash.areas.service.exception.ServiceNotFoundException;
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
		this.serviceRepository.save(service);
	}

	@Override
	public List<Service> findAllServices() throws ServiceDBEmptyException {
		List<Service> services = this.serviceRepository.findAll();
		if (services.isEmpty()) {
			throw new ServiceDBEmptyException("Service database is empty", 404);
		}

		return services;
	}

	@Override
	public Service findServiceById(Long id) throws ServiceNotFoundException {
		Service service = this.serviceRepository.findServiceById(id);
		if (service == null) {
			throw new ServiceNotFoundException("This service is not found in database", 404);
		}

		return service;
	}

	@Override
	public Service findServiceByName(String name) throws ServiceNotFoundException {
		Service service = this.serviceRepository.findServiceByServiceName(name);
		if (service == null) {
			throw new ServiceNotFoundException("This service is not found in database", 404);
		}

		return service;
	}
}
