package space.nyuki.qclient.pojo.answer;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.nyuki.qclient.group.GroupView;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ning
 * @createTime 12/1/19 1:53 PM
 * @description 时间型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryDate implements AnswerCell {
	@NotNull(
			message = "日期不能为空",
			groups = {
					GroupView.Input.class
			}
	)
	private Boolean vdate;
	private Boolean vtime;
	private List<Integer> index;
}