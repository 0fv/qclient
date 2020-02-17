package space.nyuki.qclient.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import space.nyuki.qclient.group.GroupView;


import javax.validation.constraints.Future;
import java.util.Date;
import java.util.List;

@Data
public class QuestionnaireEntity {
	private String id;
	@JsonView({
			GroupView.View.class,
			GroupView.InputView.class
	})
	private String title;
	@JsonView({
			GroupView.View.class,
			GroupView.InputView.class
	})
	private String introduce;
	@JsonView({
			GroupView.View.class,
	})
	@JsonProperty("created_account")
	private String createdAccount;
	@JsonView({
			GroupView.View.class,
	})
	private Date from;
	@JsonView({
			GroupView.View.class,
			GroupView.UpdateTime.class
	})
	@Future(message = "必须大于当前时间")
	private Date to;
	@JsonProperty("question_group")
	@JsonView({
			GroupView.InputView.class
	})
	private List<QuestionGroup> questionGroups;
	@JsonProperty("is_anonymous")
	@JsonView(GroupView.View.class)
	//0 匿名 //1 不匿名
	private Integer isAnonymous;
	@JsonView(GroupView.View.class)
	@JsonProperty("member_group_name")
	private List<String> memberGroupName;
	private List<Member> members;
	//0 不分页，//1 按组分页 //2 按pageSize条数分页
	@JsonView({
			GroupView.InputView.class
	})
	private Integer pagination;
	@JsonView({
			GroupView.InputView.class
	})
	@JsonProperty("page_size")
	private Integer pageSize;
	@JsonView(GroupView.InputView.class)
	@JsonProperty("submit_result_template")
	private SubmitResult submitResultTemplate;
	@JsonProperty("is_finish")
	private int isFinish;
	@JsonProperty("is_delete")
	private int isDelete;


}
