package space.nyuki.qclient.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import space.nyuki.qclient.exception.SubmitResultFailedException;
import space.nyuki.qclient.handler.AbstractValidHandler;
import space.nyuki.qclient.pojo.*;
import space.nyuki.qclient.pojo.result.ResultCell;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
public class ResultService {
	@Autowired
	private QuestionnaireEntityService questionnaireEntityService;
	@Autowired
	private AbstractValidHandler validHandler;
	@Autowired
	private RabbitAdmin rabbitAdmin;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private RabbitTemplate.ConfirmCallback confirmCallback;
	@Autowired
	private AESService<SubmitResult> aesService;


	private void valid(SubmitResult submitResult) {
		QuestionnaireEntity entity = questionnaireEntityService.getDataById(submitResult.getId());
		List<SubmitResultGroup> submitResultGroups = submitResult.getSubmitResultGroups();
		List<QuestionGroup> questionGroups = entity.getQuestionGroups();
		for (QuestionGroup questionGroup : questionGroups) {
			List<QuestionCell> questionCells = questionGroup.getQuestionCells();
			for (QuestionCell questionCell : questionCells) {
				List<Integer> index = questionCell.getIndex();
				ResultCell resultCell = submitResultGroups.get(index.get(0)).getResultCells().get(index.get(1));
				validHandler.validMessage(questionCell,resultCell);
			}
		}
	}

	@SneakyThrows
	public void getResult(String encryptData) {
		SubmitResult submitResult =
				aesService.decrypt(encryptData, SubmitResult.class).get(10, TimeUnit.SECONDS);
		if (Objects.nonNull(submitResult)) {
			sendResult(submitResult);
		} else {
			throw new SubmitResultFailedException();
		}
	}

	public void sendResult(SubmitResult submitResult) {
		questionnaireEntityService.answeredCheck(submitResult.getId(), submitResult.getFingerPrint());
		valid(submitResult);
		RabbitTemplate rabbitTemplate = rabbitAdmin.getRabbitTemplate();
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.convertAndSend("result", submitResult.getId(), submitResult);
	}
}
