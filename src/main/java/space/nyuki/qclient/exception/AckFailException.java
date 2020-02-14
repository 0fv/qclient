package space.nyuki.qclient.exception;

public class AckFailException extends RuntimeException {
	public AckFailException() {
		super();
	}

	public AckFailException(String message) {
		super(message);
	}
}
