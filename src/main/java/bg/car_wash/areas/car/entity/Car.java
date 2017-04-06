package bg.car_wash.areas.car.entity;

import bg.car_wash.areas.customer.entity.Customer;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "car_registration_number", nullable = false)
	@Size(min = 8, max = 10)
	private String carRegistrationNumber;

	@Column(name = "car_model_name", nullable = false)
	@Size(min = 2, max = 25)
	private String carModelName;

	@Column(name = "car_make", nullable = false)
	@Size(min = 2, max = 25)
	private String carMake;

	@Enumerated(EnumType.STRING)
	@Column(name = "car_type")
	private CarType carType;

	@ManyToOne(cascade = CascadeType.ALL)
	private Customer owner;

	public Car() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarRegistrationNumber() {
		return carRegistrationNumber;
	}

	public void setCarRegistrationNumber(String carRegistrationNumber) {
		this.carRegistrationNumber = carRegistrationNumber;
	}

	public String getCarModelName() {
		return carModelName;
	}

	public void setCarModelName(String carModelName) {
		this.carModelName = carModelName;
	}

	public String getCarMake() {
		return carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}
}
