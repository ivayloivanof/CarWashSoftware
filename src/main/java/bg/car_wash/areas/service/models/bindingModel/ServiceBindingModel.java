package bg.car_wash.areas.service.models.bindingModel;

import bg.car_wash.areas.car.entity.CarType;
import bg.car_wash.areas.user.entity.UserType;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class ServiceBindingModel {

	private  Long id;

	@Size(min = 3, max = 50, message = "Service name between 3 and 50 characters!")
	private String serviceName;

	@Digits(integer = 2, fraction = 2)
	@DecimalMin(value = "0.00", message = "Service price can not be negative number!")
	private BigDecimal servicePrice;

	private CarType carType;

	private UserType userType;

	public ServiceBindingModel() {
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
}
