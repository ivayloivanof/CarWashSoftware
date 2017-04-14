package bg.car_wash.areas.activity.exception;

/**
 * Created by ivanof on 14.04.17.
 */
public class ActivityNotFoundException extends RuntimeException {

	private String message;

	private int code;

	public ActivityNotFoundException(String message, int code) {
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