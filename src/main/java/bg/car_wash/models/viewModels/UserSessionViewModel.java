package bg.car_wash.models.viewModels;

public class UserSessionViewModel {

	private String email;

	private String fullName;

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
}
