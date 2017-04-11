package bg.car_wash.areas.customer.models.bindingModel;

import javax.validation.constraints.Size;
import java.util.Date;

public class CustomerBindingModel {

	@Size(min = 2, max = 50, message = "This customer name is not valid!")
	private String name;

	@Size(min = 10, max = 15, message = "This phone number is not valid!")
	private String phoneNumber;

	private Date date;

	private int discount;

	public CustomerBindingModel() {
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
}
