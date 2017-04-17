package bg.car_wash.exception;

public class CarWashErrorException extends  RuntimeException {

	private String message;

	public CarWashErrorException(String message) {
		this.setMessage(message);
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
