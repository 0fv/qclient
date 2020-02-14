package space.nyuki.qclient.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;


import java.io.Serializable;
import java.util.List;

@Data

public class ResultTemplate implements Serializable {
	@Id
	private String id;
	@JsonProperty("finger_print")
	private String fingerPrint;
	@JsonProperty("result_groups")
	private List<ResultGroup> resultGroups;
}
