package bg.car_wash.areas.car.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "car_make_model")
public class CarMakeModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "car_make", nullable = false)
	@Size(min = 2, max = 50)
	private String make;

	@Column(name = "car_model", nullable = false)
	@Size(min = 1, max = 50)
	private String model;

	public CarMakeModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
}
