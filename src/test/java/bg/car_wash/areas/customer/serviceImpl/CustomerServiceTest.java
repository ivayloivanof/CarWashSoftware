package bg.car_wash.areas.customer.serviceImpl;

import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.exceptions.CustomerNotFoundException;
import bg.car_wash.areas.customer.models.viewModels.CustomerWithCarsViewModel;
import bg.car_wash.areas.customer.repository.CustomerRepository;
import bg.car_wash.areas.customer.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest {

	private static final Long VALID_ID = 1L;
	private static final Long INVALID_ID = -1L;
	private static final int VALID_DISCOUNT = 15;
	private static final String VALID_NAME = "Customer name";
	private static final String VALID_PHONE_NUMBER = "+359 896 541 235";
	private static final Date VALID_DATE = new Date();

	@MockBean
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	@Before
	public void setUp() throws Exception {
		Customer customer = new Customer();
		customer.setId(VALID_ID);
		customer.setDiscount(VALID_DISCOUNT);
		customer.setName(VALID_NAME);
		customer.setPhoneNumber(VALID_PHONE_NUMBER);
		customer.setDate(VALID_DATE);

		Mockito.when(customerRepository.findCustomerById(VALID_ID)).thenReturn(customer);
	}

	@Test
	public void findCustomerByIdGivenValidCustomerId_ShouldReturnValidCustomerId() throws Exception {
		//Act
		CustomerWithCarsViewModel customerViewModel = this.customerService.findCustomerById(VALID_ID);

		//Assert
		assertEquals(VALID_ID, customerViewModel.getId());
	}

	@Test
	public void findCustomerByIdGivenValidCustomerId_ShouldReturnValidCustomerDiscount() throws Exception {
		//Act
		CustomerWithCarsViewModel customerViewModel = this.customerService.findCustomerById(VALID_ID);

		//Assert
		assertEquals(VALID_DISCOUNT, customerViewModel.getDiscount());
	}

	@Test
	public void findCustomerByIdGivenValidCustomerId_ShouldReturnValidCustomerName() throws Exception {
		//Act
		CustomerWithCarsViewModel customerViewModel = this.customerService.findCustomerById(VALID_ID);

		//Assert
		assertEquals(VALID_NAME, customerViewModel.getName());
	}

	@Test
	public void findCustomerByIdGivenValidCustomerId_ShouldReturnValidCustomerPhoneNumber() throws Exception {
		//Act
		CustomerWithCarsViewModel customerViewModel = this.customerService.findCustomerById(VALID_ID);

		//Assert
		assertEquals(VALID_PHONE_NUMBER, customerViewModel.getPhoneNumber());
	}

	@Test
	public void findCustomerByIdGivenValidCustomerId_ShouldOneCallFindCustomerById() throws Exception {
		//Act
		CustomerWithCarsViewModel customerViewModel = this.customerService.findCustomerById(VALID_ID);

		//Assert
		Mockito.verify(this.customerRepository, Mockito.times(1)).findCustomerById(VALID_ID);
	}

	@Test(expected = CustomerNotFoundException.class)
	public void findCarByIdGivenInvalidCar_ShouldThrowException() throws Exception {
		//Act
		CustomerWithCarsViewModel customerViewModel = this.customerService.findCustomerById(INVALID_ID);
	}

}