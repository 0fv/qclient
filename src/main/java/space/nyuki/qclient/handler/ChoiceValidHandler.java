package space.nyuki.qclient.handler;

import space.nyuki.qclient.exception.SubmitResultFailedException;
import space.nyuki.qclient.pojo.QuestionCell;
import space.nyuki.qclient.pojo.answer.AnswerCell;
import space.nyuki.qclient.pojo.answer.Choice;
import space.nyuki.qclient.pojo.result.ResultCell;
import space.nyuki.qclient.pojo.result.ResultChoice;

import java.util.List;
import java.util.Objects;

public class ChoiceValidHandler extends AbstractValidHandler {
	public ChoiceValidHandler(Class<? extends AnswerCell> type) {
		this.type = type;
	}


	@Override
	protected void valid(QuestionCell questionCell, ResultCell resultCell) {
		ResultChoice resultChoice = (ResultChoice) resultCell;
		noneNullValid(resultChoice.getAnswer());
		int mustAnswer = questionCell.getMustAnswer();
		List<AnswerCell> answerCells = questionCell.getAnswerCells();
		Choice answerCell = (Choice) answerCells.get(0);
		List<String> choice = answerCell.getChoice();
		Boolean isMulti = answerCell.getIsMulti();
		if (answerCells.size() == 1) {
			choiceNoCommentValid(choice, isMulti, mustAnswer, resultChoice);
		} else {
			choiceCommentValid(choice,isMulti,mustAnswer,resultChoice);
		}
	}


	private void noneNullValid(List<String> answer) {
		if (Objects.isNull(answer)) {
			throw new SubmitResultFailedException();
		}
	}

	private boolean containExcludeOne(List<String> choice, List<String> answer) {
		int count = 0;
		for (String s : answer) {
			if (!choice.contains(s)) {
				count++;
			}
		}
		return count > 1;
	}

	private void choiceCommentValid(List<String> choice, Boolean isMulti, int mustAnswer, ResultChoice resultChoice) {
		List<String> answer = resultChoice.getAnswer();
		if (isMulti) {
			if (mustAnswer == 1) {
				if (answer.size() == 0 || (containExcludeOne(choice, answer))) {
					throw new SubmitResultFailedException();
				}
			} else {
				if (containExcludeOne(choice, answer)) {
					throw new SubmitResultFailedException();
				}
			}
		} else {
			if (mustAnswer == 1) {
				if (answer.size() != 1) {
					throw new SubmitResultFailedException();
				}
			} else {
				if (answer.size() > 1) {
					throw new SubmitResultFailedException();
				}
			}
		}
	}

	private void choiceNoCommentValid(List<String> choice, Boolean isMulti, int mustAnswer, ResultChoice resultChoice) {
		List<String> answer = resultChoice.getAnswer();
		if (isMulti) {
			if (mustAnswer == 1) {
				if (answer.size() == 0 || (!choice.containsAll(answer))) {
					throw new SubmitResultFailedException();
				}
			} else {
				if (!choice.containsAll(answer)) {
					throw new SubmitResultFailedException();
				}
			}

		} else {
			if (mustAnswer == 1) {
				if (answer.size() != 1 || (!choice.containsAll(answer))) {
					throw new SubmitResultFailedException();
				}
			} else {
				if (answer.size() > 1 || (!choice.containsAll(answer))) {
					throw new SubmitResultFailedException();
				}
			}
		}
	}
}
