package bg.car_wash.areas.user.models.bindingModels.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

	@NotNull
	@Size(max = 100, message = "Invalid email")
	private String email;

	@NotNull
	@Size(min = 4, max = 15, message = "Invalid Password")
	private String password;

	public UserLoginBindingModel() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
