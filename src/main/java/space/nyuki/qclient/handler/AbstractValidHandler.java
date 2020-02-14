package space.nyuki.qclient.handler;

import space.nyuki.qclient.pojo.answer.AnswerCell;

public abstract class AbstractValidHandler {
	protected Class<? extends AnswerCell> type;
	protected AbstractValidHandler nextHandler;

	public void setNextHandler(AbstractValidHandler abstractValidHandler) {
		this.nextHandler = abstractValidHandler;
	}

	public void validMessage(AnswerCell answerCell) {
		Class<? extends AnswerCell> type = answerCell.getClass();
		if (type.equals(this.type)) {
			valid(answerCell);
		}else{
			this.nextHandler.validMessage(answerCell);
		}
	}

	abstract protected void valid(AnswerCell answerCell);

}
