package space.nyuki.qclient.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;
import space.nyuki.qclient.group.GroupView;
import space.nyuki.qclient.pojo.answer.AnswerCell;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ning
 * @createTime 12/1/19 1:38 PM
 * @description 单个问题
 */
//@JsonDeserialize
@Data
@JsonView({
		Object.class
})
public class QuestionCell {
	@NotBlank(
			message = "标题不能为空",
			groups = {GroupView.Create.class
			}
	)
	private String title;
	@NotNull(
			message = "题目内容不能为空",
			groups = {GroupView.Create.class
			}
	)
	@JsonProperty("answer_cells")
	private List<AnswerCell> answerCells;
	// 0 非必答 1 必答
	@NotNull(message = "请选择是否必答",
			groups = {
					GroupView.Create.class
			})
	@JsonProperty("must_answer")
	private int mustAnswer;
	private List<Integer> index;

}