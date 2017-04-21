package bg.car_wash.areas.customer.models.viewModels;

import bg.car_wash.areas.car.models.viewModel.CarWithoutOwnerViewModel;
import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

public class CustomerWithCarsViewModel {

	@Expose
	private Long id;

	@Expose
	private String name;

	@Expose
	private String phoneNumber;

	@Expose
	private Date date;

	@Expose
	private int discount;

	@Expose
	private List<CarWithoutOwnerViewModel> cars;

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

	public List<CarWithoutOwnerViewModel> getCars() {
		return cars;
	}

	public void setCars(List<CarWithoutOwnerViewModel> cars) {
		this.cars = cars;
	}
}
