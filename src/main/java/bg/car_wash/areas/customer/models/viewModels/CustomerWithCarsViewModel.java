package bg.car_wash.areas.customer.models.viewModels;

import bg.car_wash.areas.car.entity.Car;
import bg.car_wash.areas.car.models.viewModel.CarViewModel;

import java.util.Date;
import java.util.List;

public class CustomerWithCarsViewModel {

	private Long id;

	private String name;

	private String phoneNumber;

	private Date date;

	private int discount;

	private List<CarViewModel> cars;

	public CustomerWithCarsViewModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public List<CarViewModel> getCars() {
		return this.cars;
	}

	public void setCars(List<CarViewModel> cars) {
		this.cars = cars;
	}
}
