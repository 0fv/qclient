package space.nyuki.qclient.handler;

import space.nyuki.qclient.exception.AnswerNotCorrectException;
import space.nyuki.qclient.pojo.answer.AnswerCell;
import space.nyuki.qclient.pojo.answer.Choice;

import java.util.List;

public class ChoiceValidHandler extends AbstractValidHandler {
	public ChoiceValidHandler(Class<? extends AnswerCell> type) {
		this.type = type;
	}

	@Override
	public void valid(AnswerCell answerCell) {
		Choice choice = (Choice) answerCell;
		answerInChoice(choice);
		if (!choice.getIsMulti() && choice.getAnswer().size() != 1) {
			throw new AnswerNotCorrectException();
		}
	}

	private void answerInChoice(Choice choice) {
		List<String> answer = choice.getAnswer();
		List<String> choice1 = choice.getChoice();
		if (!choice1.containsAll(answer)) {
			throw new AnswerNotCorrectException();
		}
	}

}
