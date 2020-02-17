package space.nyuki.qclient.handler;

import space.nyuki.qclient.exception.SubmitResultFailedException;
import space.nyuki.qclient.pojo.QuestionCell;
import space.nyuki.qclient.pojo.answer.AnswerCell;
import space.nyuki.qclient.pojo.answer.Comment;
import space.nyuki.qclient.pojo.result.ResultCell;
import space.nyuki.qclient.pojo.result.ResultComment;

import java.util.Objects;

public class CommentValidHandler extends AbstractValidHandler {
	public CommentValidHandler(Class<? extends AnswerCell> type) {
		this.type = type;
	}


	@Override
	protected void valid(QuestionCell questionCell, ResultCell resultCell) {
		Comment answerCell = (Comment) questionCell.getAnswerCells().get(0);
		ResultComment resultComment = (ResultComment) resultCell;
		if (questionCell.getMustAnswer() == 1) {
			String answer = resultComment.getAnswer();
			if (Objects.isNull(answer) || answer.isEmpty()) {
				throw new SubmitResultFailedException();
			}
		}
	}
}
