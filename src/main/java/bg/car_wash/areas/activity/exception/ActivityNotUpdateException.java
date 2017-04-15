package bg.car_wash.areas.activity.exception;

public class ActivityNotUpdateException extends Exception {

	private String message;

	private int code;

	public ActivityNotUpdateException(String message, int code) {
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
