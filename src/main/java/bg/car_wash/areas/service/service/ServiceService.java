package bg.car_wash.areas.service.service;

import bg.car_wash.areas.service.exceptions.ServiceDBEmptyException;
import bg.car_wash.areas.service.exceptions.ServiceNotCreateException;
import bg.car_wash.areas.service.exceptions.ServiceNotFoundException;
import bg.car_wash.areas.service.exceptions.ServiceNotUpdateException;
import bg.car_wash.areas.service.models.bindingModel.ServiceBindingModel;
import bg.car_wash.areas.service.models.viewModel.ServiceViewModel;

import java.util.List;

public interface ServiceService {

	void createService(ServiceBindingModel serviceBindingModel) throws ServiceNotCreateException;

	List<ServiceViewModel> findAllServices() throws ServiceDBEmptyException;

	ServiceViewModel findServiceById(Long id) throws ServiceNotFoundException;

	ServiceViewModel findServiceByName(String name) throws ServiceNotFoundException;

	void updateService(ServiceBindingModel serviceBindingModel) throws ServiceNotUpdateException;

	void deleteServiceById(Long id) throws ServiceNotFoundException;

	ServiceViewModel getServiceViewModel(Long id);
}
