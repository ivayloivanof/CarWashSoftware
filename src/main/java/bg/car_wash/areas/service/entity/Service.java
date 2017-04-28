package bg.car_wash.areas.service.entity;

import bg.car_wash.areas.activity.entity.Activity;
import bg.car_wash.areas.car.entities.Car;
import bg.car_wash.areas.car.entities.CarType;
import bg.car_wash.areas.user.entity.UserType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "services")
public class Service implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "service_name", nullable = false)
	@Size(min = 3, max = 50)
	private String serviceName;

	@Column(name = "service_price", nullable = false)
	private BigDecimal servicePrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "car_type")
	private CarType carType;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_type")
	private UserType userType;

	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Activity.class)
	private List<Activity> activities;

	@ManyToMany
	private List<Car> cars;

	public Service() {
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

	public List<Activity> getActivities() {
		return this.activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
}
