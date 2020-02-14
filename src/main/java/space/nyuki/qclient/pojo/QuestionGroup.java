package space.nyuki.qclient.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import space.nyuki.qclient.group.GroupView;


import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ning
 * @createTime 12/22/19 2:37 PM
 * @description 问题组
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonView(
		Object.class
)
public class QuestionGroup {
	@NotNull(
			message = "问题组名不能为空",
			groups = {GroupView.Create.class}
	)
	private String title;
	@JsonProperty("question_cells")
	private List<QuestionCell> questionCells;
}