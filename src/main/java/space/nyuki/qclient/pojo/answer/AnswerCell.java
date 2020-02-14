package space.nyuki.qclient.pojo.answer;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonView;

import java.io.Serializable;
import java.util.List;

/**
 * @author ning
 * @createTime 12/1/19 1:46 PM
 * @description 不同类型答案的父类
 */
@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		property = "type"
)
@JsonSubTypes({
		@JsonSubTypes.Type(value = Choice.class, name = "choice"),
		@JsonSubTypes.Type(value = Comment.class, name = "comment"),
		@JsonSubTypes.Type(value = InquiryDate.class, name = "date")
})
@JsonView(Object.class)
public interface AnswerCell extends Serializable {
	void setIndex(List<Integer> index);
}