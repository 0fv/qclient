package space.nyuki.qclient.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import space.nyuki.qclient.group.GroupView;

import javax.validation.constraints.Email;

@Data
public class Member {
	private String id;
	private String gid;

	private String name;
	@Email(
			message = "请输入正确的邮件地址",
			groups = GroupView.Create.class
	)
	@JsonView(GroupView.View.class)
	private String email;
	@JsonProperty("additional_info")
	@JsonView(GroupView.View.class)
	private String additionalInfo;
}
