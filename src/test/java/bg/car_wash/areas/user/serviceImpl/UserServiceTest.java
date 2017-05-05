package bg.car_wash.areas.user.serviceImpl;

import bg.car_wash.areas.user.entity.User;
import bg.car_wash.areas.user.exception.UserNotFoundException;
import bg.car_wash.areas.user.repository.UserRepository;
import bg.car_wash.areas.user.service.UserService;
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
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

	private static final Long VALID_ID = 1L;
	private static final Long INVALID_ID = -1L;
	private static final String VALID_EMAIL = "ivan@abv.bg";

	@MockBean
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		User user = new User();
		user.setId(VALID_ID);
		user.setEmail(VALID_EMAIL);
		user.setEnabled(true);
		user.setCredentialsNonExpired(true);

		Mockito.when(userRepository.findUserById(VALID_ID)).thenReturn(user);
	}

	@Test
	public void findUserByIdGivenValidId_ShouldReturnValidUserId() throws Exception {
		//Act
		User user = this.userService.findUserById(VALID_ID);

		//Assert
		assertEquals(VALID_ID, user.getId());
	}

	@Test
	public void findUserByIdGivenValidId_ShouldReturnValidUserEmail() throws Exception {
		//Act
		User user = this.userService.findUserById(VALID_ID);

		//Assert
		assertEquals(VALID_EMAIL, user.getEmail());
	}

	@Test
	public void findUserByIdGivenValidId_ShouldReturnIsEnabled() throws Exception {
		//Act
		User user = this.userService.findUserById(VALID_ID);

		//Assert
		assertTrue(user.isEnabled());
	}

	@Test
	public void findUserByIdGivenValidId_ShouldReturnIsCredentialsNonExpired() throws Exception {
		//Act
		User user = this.userService.findUserById(VALID_ID);

		//Assert
		assertTrue(user.isCredentialsNonExpired());
	}

	@Test
	public void findUserByIdGivenValidId_ShouldOneCallFindUserById() throws Exception {
		//Act
		User user = this.userService.findUserById(VALID_ID);

		//Assert
		Mockito.verify(this.userRepository, Mockito.times(1)).findUserById(VALID_ID);
	}

	@Test(expected = UserNotFoundException.class)
	public void findUserByIdGivenValidId_ShouldThrowException() throws Exception {
		//Act
		User user = this.userService.findUserById(INVALID_ID);
	}

}