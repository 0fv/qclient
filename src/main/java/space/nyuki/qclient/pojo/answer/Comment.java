package space.nyuki.qclient.pojo.answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.nyuki.qclient.group.GroupView;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ning
 * @createTime 12/1/19 1:52 PM
 * @description 评论型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements AnswerCell {
	@NotNull(
			message = "评论不能为空",
			groups = {
					GroupView.Input.class
			}
	)
	private String comment;
	private Integer limit;
	private Integer line;
	private Boolean empty;
	private List<Integer> index;

}