package bg.car_wash.models.viewModels.user;

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

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("name: %s | email: %s", this.getFullName(), this.getEmail()));
		return stringBuilder.toString();
	}
}
