package space.nyuki.qclient.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import space.nyuki.qclient.factory.TransFactory;
import space.nyuki.qclient.group.GroupView;
import space.nyuki.qclient.pojo.QuestionnaireEntity;
import space.nyuki.qclient.pojo.TransData;
import space.nyuki.qclient.service.QuestionnaireEntityService;

@RestController
@RequestMapping("questionnaireEntity")
public class QuestionnaireEntityController {
	@Autowired
	private QuestionnaireEntityService questionnaireEntityService;

	@GetMapping("{id}/{mId}")
	@JsonView(GroupView.InputView.class)
	public TransData getDataById(@PathVariable(name = "id") String id, @PathVariable(name = "mId") String mId) {
		return TransFactory.getSuccessResponse(questionnaireEntityService.getDataById(id, mId));
	}
	@GetMapping("{id}")
	@JsonView(GroupView.InputView.class)
	public TransData getDataById2(@PathVariable(name = "id") String id,
	                              @RequestParam(name="fingerprint")String fingerPrint) {
		return TransFactory.getSuccessResponse(questionnaireEntityService.getDataById(id,true,fingerPrint));
	}
}
