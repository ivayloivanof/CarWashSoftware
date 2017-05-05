package bg.car_wash.areas.activity.controllers;

import bg.car_wash.areas.activity.service.ActivityService;
import bg.car_wash.configurations.site.PageTitle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ActivityController.class)
@ActiveProfiles("test")
public class ActivityControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ActivityService activityService;

	@Test
	public void getAllActivitiesGivenValidActivityModel_ShouldReturnValidActivitiesViewModel() throws Exception {
		ResultActions resultActions = this.mvc.perform(get("/activity/all"))
				.andExpect(status().isOk())
				.andExpect(view().name("activity/activity-all"))
				.andExpect(model().attribute("pageTitle", hasProperty("pageTitle", is(PageTitle.ACTIVITY_ALL_PAGE))));

	}

}