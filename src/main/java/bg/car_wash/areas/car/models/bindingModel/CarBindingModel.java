package bg.car_wash.areas.car.models.bindingModel;

import bg.car_wash.areas.car.entity.CarType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CarBindingModel {

	@Size(min = 8, max = 10, message = "Error Car registration Number")
	private String carRegistrationNumber;

	@Size(min = 3, max = 25, message = "Error car model name")
	private String carModel;

	@Size(min = 3, max = 25, message = "Error car make")
	private String carMake;

	private CarType carType;

	public CarBindingModel() {
	}

	public String getCarRegistrationNumber() {
		return carRegistrationNumber;
	}

	public void setCarRegistrationNumber(String carRegistrationNumber) {
		this.carRegistrationNumber = carRegistrationNumber;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModelName) {
		this.carModel = carModelName;
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
}
