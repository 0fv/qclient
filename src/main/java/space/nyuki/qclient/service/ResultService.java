package space.nyuki.qclient.service;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import space.nyuki.qclient.exception.TemplateNotFoundException;
import space.nyuki.qclient.handler.AbstractValidHandler;
import space.nyuki.qclient.pojo.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class ResultService {
	@Autowired
	private QuestionnaireEntityService questionnaireEntityService;
	@Autowired
	private AbstractValidHandler validHandler;
	@Autowired
	private RabbitAdmin rabbitAdmin;
	@Autowired
	private RabbitTemplate.ConfirmCallback confirmCallback;

	public ResultTemplate getTemplate(String id) {
		ResultTemplate resultTemplate;
		QuestionnaireEntity entity = questionnaireEntityService.getDataById(id);
		if (Objects.nonNull(entity)) {
			List<QuestionGroup> questionGroups = entity.getQuestionGroups();
			resultTemplate = getResultTemplate(id, questionGroups);
		} else {
			throw new TemplateNotFoundException();
		}
		return resultTemplate;
	}

	private ResultTemplate getResultTemplate(String id, List<QuestionGroup> questionGroups) {
		List<ResultGroup> resultGroups = new ArrayList<>();
		questionGroups.forEach(n -> {
			ResultGroup resultGroup = new ResultGroup();
			resultGroup.setTitle(n.getTitle());
			List<Result> results = new ArrayList<>();
			n.getQuestionCells().forEach(m -> {
				Result result = new Result();
				result.setTitle(m.getTitle());
				result.setAnswerCells(m.getAnswerCells());
				result.setMustAnswer(m.getMustAnswer());
				results.add(result);
			});
			resultGroup.setResults(results);
			resultGroups.add(resultGroup);
		});
		ResultTemplate resultTemplate = new ResultTemplate();
		resultTemplate.setId(id);
		resultTemplate.setResultGroups(resultGroups);
		return resultTemplate;
	}

	private void valid(ResultTemplate resultTemplate) {
		resultTemplate.getResultGroups().stream()
				.map(ResultGroup::getResults)
				.flatMap(Collection::stream)
				.filter(n -> n.getMustAnswer() == 1)
				.map(Result::getAnswerCells)
				.flatMap(Collection::stream)
				.forEach(this.validHandler::validMessage);
	}

	public void sendResult(ResultTemplate resultTemplate) {
		questionnaireEntityService.answeredCheck(resultTemplate.getId(),resultTemplate.getFingerPrint());
		valid(resultTemplate);
		RabbitTemplate rabbitTemplate = rabbitAdmin.getRabbitTemplate();
		rabbitTemplate.setConfirmCallback(confirmCallback);
		rabbitTemplate.convertAndSend("result", resultTemplate.getId(), resultTemplate);
	}
}
