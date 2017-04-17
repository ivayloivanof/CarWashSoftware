package bg.car_wash.areas.service.exception;

public class ServiceNotCreateException extends RuntimeException {
	private String message;

	private int code;

	public ServiceNotCreateException(String message, int code) {
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
