package bg.car_wash.areas.car.models.viewModel;

import bg.car_wash.areas.car.entity.CarType;
import bg.car_wash.areas.customer.entity.Customer;
import bg.car_wash.areas.customer.models.viewModels.CustomerViewModel;

public class CarViewModel {

	private Long id;

	private String carRegistrationNumber;

	private String carModel;

	private String carMake;

	private CarType carType;

	private CustomerViewModel owner;

	public CarViewModel() {
	}

	public String getCarRegistrationNumber() {
		return carRegistrationNumber;
	}

	public void setCarRegistrationNumber(String carRegistrationNumber) {
		this.carRegistrationNumber = carRegistrationNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
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

	public CustomerViewModel getOwner() {
		return owner;
	}

	public void setOwner(CustomerViewModel owner) {
		this.owner = owner;
	}
}
