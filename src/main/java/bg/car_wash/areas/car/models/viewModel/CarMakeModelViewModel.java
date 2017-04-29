package bg.car_wash.areas.car.models.viewModel;

public class CarMakeModelViewModel {
	private Long id;

	private String model;

	private String make;

	public CarMakeModelViewModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}
}
