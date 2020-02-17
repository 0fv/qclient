package space.nyuki.qclient.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.nyuki.qclient.factory.TransFactory;
import space.nyuki.qclient.group.GroupView;
import space.nyuki.qclient.pojo.QuestionnaireEntity;
import space.nyuki.qclient.pojo.TransData;
import space.nyuki.qclient.service.AESService;
import space.nyuki.qclient.service.QuestionnaireEntityService;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("questionnaireEntity")
public class QuestionnaireEntityController {
	@Autowired
	private QuestionnaireEntityService questionnaireEntityService;
	@Autowired
	private AESService<QuestionnaireEntity> aesService;

	@GetMapping("{id}/{mId}")
	public TransData getDataById(@PathVariable(name = "id") String id, @PathVariable(name = "mId") String mId)
			throws ExecutionException, InterruptedException {
		return TransFactory.getSuccessResponse
				(aesService.encrypt(questionnaireEntityService.getDataById(id, mId),GroupView.InputView.class).get());
	}

	@GetMapping("{id}")
	public TransData getDataById2(@PathVariable(name = "id") String id,
	                              @RequestParam(name = "fingerprint") String fingerPrint)
			throws ExecutionException, InterruptedException {
		return TransFactory
				.getSuccessResponse(aesService
						.encrypt(questionnaireEntityService.getDataById
								(id, true, fingerPrint),GroupView.InputView.class)
						.get());
	}
}
