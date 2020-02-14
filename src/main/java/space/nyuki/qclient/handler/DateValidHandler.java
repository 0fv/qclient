package space.nyuki.qclient.handler;

import space.nyuki.qclient.exception.AnswerNotCorrectException;
import space.nyuki.qclient.pojo.answer.AnswerCell;
import space.nyuki.qclient.pojo.answer.InquiryDate;

import java.util.Date;
import java.util.Objects;

public class DateValidHandler extends AbstractValidHandler {
	public DateValidHandler(Class<? extends AnswerCell> type) {
		this.type = type;
	}

	@Override
	protected void valid(AnswerCell answerCell) {
		InquiryDate date = (InquiryDate) answerCell;
		Date answer = date.getAnswer();
		if (Objects.isNull(answer)) {
			throw new AnswerNotCorrectException();
		}
	}
}
