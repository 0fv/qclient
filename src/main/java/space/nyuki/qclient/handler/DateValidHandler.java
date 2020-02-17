package space.nyuki.qclient.handler;

import space.nyuki.qclient.exception.SubmitResultFailedException;
import space.nyuki.qclient.pojo.QuestionCell;
import space.nyuki.qclient.pojo.answer.AnswerCell;
import space.nyuki.qclient.pojo.answer.InquiryDate;
import space.nyuki.qclient.pojo.result.ResultCell;
import space.nyuki.qclient.pojo.result.ResultDate;

import java.util.Date;
import java.util.Objects;

public class DateValidHandler extends AbstractValidHandler {
	public DateValidHandler(Class<? extends AnswerCell> type) {
		this.type = type;
	}


	@Override
	protected void valid(QuestionCell questionCell, ResultCell resultCell) {
		InquiryDate inquiryDate = (InquiryDate) questionCell.getAnswerCells().get(0);
		if (questionCell.getMustAnswer() == 1) {
			ResultDate resultDate = (ResultDate) resultCell;
			Date answer = resultDate.getAnswer();
			if (Objects.isNull(answer)) {
				throw new SubmitResultFailedException();
			}
		}

	}
}
