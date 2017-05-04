package bg.car_wash.areas.service.serviceImpl;

import bg.car_wash.areas.car.entities.CarType;
import bg.car_wash.areas.service.entity.Service;
import bg.car_wash.areas.service.exceptions.ServiceNotFoundException;
import bg.car_wash.areas.service.models.viewModel.ServiceViewModel;
import bg.car_wash.areas.service.repository.ServiceRepository;
import bg.car_wash.areas.service.service.ServiceService;
import bg.car_wash.areas.user.entity.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ServiceServiceTest {

	private static final Long VALID_ID = 1L;
	private static final Long INVALID_ID = -1L;
	private static final CarType VALID_CAR_TYPE = CarType.CAR;
	private static final String VALID_SERVICE_NAME = "Service name";
	private static final UserType VALID_USER_TYPE = UserType.ADMIN;

	@MockBean
	private ServiceRepository serviceRepository;

	@Autowired
	private ServiceService serviceService;

	@Before
	public void setUp() throws Exception {
		Service service = new Service();
		service.setId(VALID_ID);
		service.setCarType(VALID_CAR_TYPE);
		service.setServiceName(VALID_SERVICE_NAME);
		service.setUserType(VALID_USER_TYPE);

		Mockito.when(serviceRepository.findServiceById(VALID_ID)).thenReturn(service);
	}

	@Test
	public void findServiceByIdGivenValidServiceId_ShouldReturnValidCarId() throws Exception {
		//Act
		ServiceViewModel serviceViewModel = this.serviceService.findServiceById(VALID_ID);

		//Assert
		assertEquals(VALID_ID, serviceViewModel.getId());
	}

	@Test
	public void findServiceByIdGivenValidServiceId_ShouldReturnValidCarType() throws Exception {
		//Act
		ServiceViewModel serviceViewModel = this.serviceService.findServiceById(VALID_ID);

		//Assert
		assertEquals(VALID_CAR_TYPE, serviceViewModel.getCarType());
	}

	@Test
	public void findServiceByIdGivenValidServiceId_ShouldReturnValidServiceName() throws Exception {
		//Act
		ServiceViewModel serviceViewModel = this.serviceService.findServiceById(VALID_ID);

		//Assert
		assertEquals(VALID_SERVICE_NAME, serviceViewModel.getServiceName());
	}

	@Test
	public void findServiceByIdGivenValidServiceId_ShouldReturnValidUserType() throws Exception {
		//Act
		ServiceViewModel serviceViewModel = this.serviceService.findServiceById(VALID_ID);

		//Assert
		assertEquals(VALID_USER_TYPE, serviceViewModel.getUserType());
	}

	@Test
	public void findServiceByIdGivenValidServiceId_ShouldOneCallFindServiceById() throws Exception {
		//Act
		ServiceViewModel serviceViewModel = this.serviceService.findServiceById(VALID_ID);

		//Assert
		Mockito.verify(this.serviceRepository, Mockito.times(1)).findServiceById(VALID_ID);
	}

	@Test(expected = ServiceNotFoundException.class)
	public void findServiceByIdGivenInvalidServiceId_ShouldThrowException() throws Exception {
		//Act
		ServiceViewModel serviceViewModel = this.serviceService.findServiceById(INVALID_ID);
	}
}