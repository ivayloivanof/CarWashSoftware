package bg.car_wash.areas.activity.entity;

import bg.car_wash.areas.car.entity.Car;
import bg.car_wash.areas.service.entity.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "activities")
public class Activity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "activity_name", nullable = false)
	private String activityName;

	@Column(name = "activity_price", nullable = false)
	private BigDecimal activityPrice;

	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Service.class)
	private List<Service> services;

	@ManyToMany
	private List<Car> cars;

	public Activity() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public BigDecimal getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Car> getCar() {
		return cars;
	}

	public void setCar(List<Car> car) {
		this.cars = car;
	}
}
