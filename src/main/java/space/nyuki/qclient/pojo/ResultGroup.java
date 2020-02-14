package space.nyuki.qclient.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultGroup implements Serializable {
	private String title;
	private List<Result> results;
}
