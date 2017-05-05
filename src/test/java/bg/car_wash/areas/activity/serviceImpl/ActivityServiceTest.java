package bg.car_wash.areas.activity.serviceImpl;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.exceptions.ActivityNotFoundException;
import bg.car_wash.areas.activity.models.viewModel.ActivityViewModel;
import bg.car_wash.areas.activity.repository.ActivityRepository;
import bg.car_wash.areas.activity.service.ActivityService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ActivityServiceTest {

	public static final Long VALID_ACTIVITY_ID = 1L;
	public static final Long INVALID_ACTIVITY_ID = -1L;
	public static final String ACTIVITY_NAME = "Activity 1";
	public static final BigDecimal ACTIVITY_PRICE = new BigDecimal("15");

	@MockBean
	private ActivityRepository activityRepository;

	@Autowired
	private ActivityService activityService;

	@Before
	public void setUp() throws Exception {
		Activity activity = new Activity();
		activity.setId(VALID_ACTIVITY_ID);
		activity.setActivityName(ACTIVITY_NAME);
		activity.setActivityPrice(ACTIVITY_PRICE);

		Mockito.when(activityRepository.findActivityById(1L)).thenReturn(activity);
	}

	@Test
	public void findActivityByIdGivenValidActivity_ShouldReturnValidActivityId() throws Exception {
		//Act
		ActivityViewModel activityViewModel = this.activityService.findActivityById(VALID_ACTIVITY_ID);

		//Assert
		assertEquals(VALID_ACTIVITY_ID, activityViewModel.getId());
	}

	@Test
	public void findActivityByIdGivenValidActivity_ShouldReturnValidActivityName() throws Exception {
		//Act
		ActivityViewModel activityViewModel = this.activityService.findActivityById(VALID_ACTIVITY_ID);

		//Assert
		assertEquals(ACTIVITY_NAME, activityViewModel.getActivityName());
	}

	@Test
	public void findActivityByIdGivenValidActivity_ShouldReturnValidActivityPrice() throws Exception {
		//Act
		ActivityViewModel activityViewModel = this.activityService.findActivityById(VALID_ACTIVITY_ID);

		//Assert
		assertEquals(ACTIVITY_PRICE, activityViewModel.getActivityPrice());
	}

	@Test
	public void findActivityByIdGivenValidActivity_ShouldOneCallFindActivityById() throws Exception {
		//Act
		ActivityViewModel activityViewModel = this.activityService.findActivityById(VALID_ACTIVITY_ID);

		//Assert
		Mockito.verify(this.activityRepository, Mockito.times(1)).findActivityById(VALID_ACTIVITY_ID);
	}

	@Test(expected = ActivityNotFoundException.class)
	public void findCarByIdGivenInvalidCar_ShouldThrowException() throws Exception {
		//Act
		ActivityViewModel activityViewModel = this.activityService.findActivityById(INVALID_ACTIVITY_ID);
	}

}