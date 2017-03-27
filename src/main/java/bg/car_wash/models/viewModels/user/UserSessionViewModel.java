package bg.car_wash.models.viewModels.user;

import bg.car_wash.areas.user.entity.UserType;

public class UserSessionViewModel {

	private String email;

	private String fullName;

	private UserType userType;

	public UserSessionViewModel() {
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
