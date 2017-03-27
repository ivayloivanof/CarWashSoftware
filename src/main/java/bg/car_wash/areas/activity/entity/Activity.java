package bg.car_wash.areas.activity.entity;

import bg.car_wash.areas.car.entity.Car;
import bg.car_wash.areas.service.entity.Service;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "activities")
public class Activity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "service_name", nullable = false)
	private String serviceName;

	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Service.class)
	private List<Service> services;

	@ManyToMany
	private List<Car> car;

	public Activity() {
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

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Car> getCar() {
		return car;
	}

	public void setCar(List<Car> car) {
		this.car = car;
	}
}
