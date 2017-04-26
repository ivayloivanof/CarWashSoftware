package bg.car_wash.areas.service.service;

import bg.car_wash.areas.service.entity.Service;
import bg.car_wash.areas.service.exceptions.ServiceDBEmptyException;
import bg.car_wash.areas.service.exceptions.ServiceNotCreateException;
import bg.car_wash.areas.service.exceptions.ServiceNotFoundException;
import bg.car_wash.areas.service.exceptions.ServiceNotUpdateException;

import java.util.List;

public interface ServiceService {

	void createService(Service service) throws ServiceNotCreateException;

	List<Service> findAllServices() throws ServiceDBEmptyException;

	Service findServiceById(Long id) throws ServiceNotFoundException;

	Service findServiceByName(String name) throws ServiceNotFoundException;

	void updateService(Service service) throws ServiceNotUpdateException;

	void deleteServiceById(Long id) throws ServiceNotFoundException;
}
