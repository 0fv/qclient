package space.nyuki.qclient.pojo.answer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.nyuki.qclient.group.GroupView;


import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ning
 * @createTime 12/1/19 1:48 PM
 * @description 选择型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Choice implements AnswerCell {
	@NotNull(
			message = "选项不能为空",
			groups = {
					GroupView.Create.class
			}
	)
	private List<String> choice;
	@NotNull(
			message = "请选择是否为多选",
			groups = {
					GroupView.Create.class
			}
	)
	@JsonProperty("is_multi")
	private Boolean isMulti;
	private List<String> answer;
	private List<Integer> index;
}