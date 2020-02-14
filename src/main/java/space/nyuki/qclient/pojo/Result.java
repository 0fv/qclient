package space.nyuki.qclient.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import space.nyuki.qclient.pojo.answer.AnswerCell;

import java.io.Serializable;
import java.util.List;


@Data
public class Result implements Serializable {
	private String title;
	@JsonProperty("answer_cells")
	private List<AnswerCell> answerCells;
	@JsonProperty("must_answer")
	private int mustAnswer;
}
