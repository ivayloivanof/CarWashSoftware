package bg.car_wash.areas.customer.models.bindingModel;

import bg.car_wash.areas.customer.annotation.ValidCustomerPhoneNumber;

import javax.validation.constraints.Size;
import java.util.Date;

public class CustomerBindingModel {

	private Long id;

	@Size(min = 2, max = 50, message = "This customer name is not valid!")
	private String name;

	@Size(min = 10, max = 16, message = "This phone number is not valid! Phone number like: '+359 111 222 333'!")
	private String phoneNumber;

	private Date date;

	private int discount;

	public CustomerBindingModel() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDiscount() {
		return this.discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
