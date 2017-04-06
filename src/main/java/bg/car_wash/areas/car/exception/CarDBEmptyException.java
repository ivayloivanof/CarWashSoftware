package bg.car_wash.areas.car.exception;

public class CarDBEmptyException extends RuntimeException {

	private String message;

	private int code;

	public CarDBEmptyException(String message, int code) {
		super(message);
		this.setMessage(message);
		this.setCode(code);
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
