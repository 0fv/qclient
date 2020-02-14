package space.nyuki.qclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.nyuki.qclient.handler.AbstractValidHandler;
import space.nyuki.qclient.handler.ChoiceValidHandler;
import space.nyuki.qclient.handler.CommentValidHandler;
import space.nyuki.qclient.handler.DateValidHandler;
import space.nyuki.qclient.pojo.answer.Choice;
import space.nyuki.qclient.pojo.answer.Comment;
import space.nyuki.qclient.pojo.answer.InquiryDate;

@Configuration
public class ValidConfig {
	@Bean
	public AbstractValidHandler abstractValidHandler() {
		AbstractValidHandler choiceValidHandler = new ChoiceValidHandler(Choice.class);
		AbstractValidHandler commentValidHandler = new CommentValidHandler(Comment.class);
		AbstractValidHandler dateValidHandler = new DateValidHandler(InquiryDate.class);
		choiceValidHandler.setNextHandler(commentValidHandler);
		commentValidHandler.setNextHandler(dateValidHandler);
		return choiceValidHandler;
	}
}
