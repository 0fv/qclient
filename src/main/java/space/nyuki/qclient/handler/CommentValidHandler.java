package space.nyuki.qclient.handler;

import space.nyuki.qclient.exception.AnswerNotCorrectException;
import space.nyuki.qclient.pojo.answer.AnswerCell;
import space.nyuki.qclient.pojo.answer.Comment;

public class CommentValidHandler extends AbstractValidHandler {
	public CommentValidHandler(Class<? extends AnswerCell> type) {
		this.type = type;
	}

	@Override
	protected void valid(AnswerCell answerCell) {
		Comment comment = (Comment) answerCell;
		String answer = comment.getAnswer();
		Integer limit = comment.getLimit();
		if (answer.length() > limit) {
			throw new AnswerNotCorrectException();
		}
	}

}
