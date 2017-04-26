package bg.car_wash.areas.car.models.viewModel;

import bg.car_wash.areas.car.entities.CarType;
import bg.car_wash.areas.customer.models.viewModels.CustomerViewModel;
import com.google.gson.annotations.Expose;

public class CarViewModel {

	@Expose
	private Long id;

	@Expose
	private String carRegistrationNumber;

	@Expose
	private String carModel;

	@Expose
	private String carMake;

	@Expose
	private CarType carType;

	@Expose
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
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public CustomerViewModel getOwner() {
		return this.owner;
	}

	public void setOwner(CustomerViewModel owner) {
		this.owner = owner;
	}
}
