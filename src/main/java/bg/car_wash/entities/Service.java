package bg.car_wash.entities;

import bg.car_wash.entities.enumerations.CarType;
import bg.car_wash.entities.enumerations.UserType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {

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
}
