package bg.car_wash.entities;

import bg.car_wash.enumerations.CarType;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "tariffs")
public class Tariff {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tariff_name", nullable = false)
	@Size(min = 3, max = 50)
	private String tariffName;

	@Column(name = "tariff_price", nullable = false)
	private BigDecimal tariffPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "car_type")
	@Size(min = 3, max = 15)
	private CarType carType;

	public Tariff() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTariffName() {
		return tariffName;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	public BigDecimal getTariffPrice() {
		return tariffPrice;
	}

	public void setTariffPrice(BigDecimal tariffPrice) {
		this.tariffPrice = tariffPrice;
	}

	public CarType getCarType() {
		return carType;
	}

	public void setCarType(CarType carType) {
		this.carType = carType;
	}
}
