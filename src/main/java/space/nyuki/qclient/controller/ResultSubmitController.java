package space.nyuki.qclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.nyuki.qclient.factory.TransFactory;
import space.nyuki.qclient.pojo.ResultTemplate;
import space.nyuki.qclient.pojo.SubmitResult;
import space.nyuki.qclient.pojo.TransData;
import space.nyuki.qclient.service.ResultService;

import java.util.Map;


@RestController
@RequestMapping("result")
public class ResultSubmitController {
	@Autowired
	private ResultService resultService;

	@PostMapping
	public TransData submitResult(@RequestBody Map<String,String> submitData) {
		resultService.getResult(submitData.get("data"));
		return TransFactory.getSuccessResponse();
	}
}
