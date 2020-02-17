package space.nyuki.qclient.handler;

import space.nyuki.qclient.pojo.QuestionCell;
import space.nyuki.qclient.pojo.answer.AnswerCell;
import space.nyuki.qclient.pojo.result.ResultCell;

public abstract class AbstractValidHandler {
	protected Class<? extends AnswerCell> type;
	protected AbstractValidHandler nextHandler;

	public void setNextHandler(AbstractValidHandler abstractValidHandler) {
		this.nextHandler = abstractValidHandler;
	}

	public void validMessage(QuestionCell questionCell, ResultCell resultCell) {
		Class<? extends AnswerCell> type = questionCell.getAnswerCells().get(0).getClass();
		if (type.equals(this.type)) {
			valid(questionCell, resultCell);
		} else {
			this.nextHandler.validMessage(questionCell, resultCell);
		}
	}

	abstract protected void valid(QuestionCell questionCell, ResultCell resultCell);

}
