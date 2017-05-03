package bg.car_wash.areas.service.models.viewModel;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.activity.models.viewModel.ActivityViewModel;
import bg.car_wash.areas.car.entities.CarType;
import bg.car_wash.areas.user.entity.UserType;
import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.List;

public class ServiceViewModel {

	@Expose
	private Long id;

	@Expose
	private String serviceName;

	@Expose
	private BigDecimal servicePrice;

	@Expose
	private CarType carType;

	@Expose
	private UserType userType;

	@Expose
	private List<ActivityViewModel> activities;

	public ServiceViewModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public BigDecimal getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public List<ActivityViewModel> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityViewModel> activities) {
		this.activities = activities;
	}
}
