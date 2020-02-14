package space.nyuki.qclient.exception;

public class AnswerNotCorrectException extends RuntimeException {
	public AnswerNotCorrectException() {
		super();
	}

	public AnswerNotCorrectException(String message) {
		super(message);
	}
}
