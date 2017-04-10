package bg.car_wash.areas.car.models.bindingModel;

import javax.validation.constraints.Size;

public class CarSearchBindingModel {

	@Size(min = 8, max = 10, message = "Error Car registration Number")
	private String carRegistrationNumber;

	public CarSearchBindingModel() {
	}

	public String getCarRegistrationNumber() {
		return carRegistrationNumber;
	}

	public void setCarRegistrationNumber(String carRegistrationNumber) {
		this.carRegistrationNumber = carRegistrationNumber;
	}
}
