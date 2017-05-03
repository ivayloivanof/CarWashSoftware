package bg.car_wash.areas.user.models.viewModels;

import bg.car_wash.areas.user.entity.UserType;
import com.google.gson.annotations.Expose;

public class UserViewModel {

	@Expose
	private Long id;

	@Expose
	private String email;

	@Expose
	private String fullName;

	@Expose
	private UserType userType;

	public UserViewModel() {
	}

	public UserViewModel(String email, String fullName, UserType userType) {
		this.setEmail(email);
		this.setFullName(fullName);
		this.setUserType(userType);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
}
