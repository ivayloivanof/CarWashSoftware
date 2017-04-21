package bg.car_wash.areas.car.models.bindingModel;

import bg.car_wash.areas.car.entity.CarType;
import bg.car_wash.areas.customer.models.viewModels.CustomerViewModel;

import javax.validation.constraints.Size;

public class CarBindingModel {

	private Long id;

	@Size(min = 7, max = 10, message = "Error Car registration Number")
	private String carRegistrationNumber;

	@Size(min = 2, max = 25, message = "Error car model name")
	private String carModel;

	@Size(min = 2, max = 25, message = "Error car make")
	private String carMake;

	private CarType carType;

	private Long customerId;

	private CustomerViewModel owner;

	public CarBindingModel() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarRegistrationNumber() {
		return this.carRegistrationNumber;
	}

	public void setCarRegistrationNumber(String carRegistrationNumber) {
		this.carRegistrationNumber = carRegistrationNumber;
	}

	public String getCarModel() {
		return this.carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarMake() {
		return this.carMake;
	}

	public void setCarMake(String carMake) {
		this.carMake = carMake;
	}

	public CarType getCarType() {
		return this.carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public CustomerViewModel getOwner() {
		return this.owner;
	}

	public void setOwner(CustomerViewModel owner) {
		this.owner = owner;
	}
}
