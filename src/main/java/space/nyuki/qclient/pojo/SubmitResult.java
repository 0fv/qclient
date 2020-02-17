package space.nyuki.qclient.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import space.nyuki.qclient.group.GroupView;

import java.util.List;

@Data
@JsonView(GroupView.InputView.class)
public class SubmitResult {
	private String id;
	@JsonProperty("finger_print")
	private String fingerPrint;
	@JsonProperty("submit_result_group")
	private List<SubmitResultGroup> submitResultGroups;
}
