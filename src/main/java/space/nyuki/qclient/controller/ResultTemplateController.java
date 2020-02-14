package space.nyuki.qclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.nyuki.qclient.factory.TransFactory;
import space.nyuki.qclient.pojo.ResultTemplate;
import space.nyuki.qclient.pojo.TransData;
import space.nyuki.qclient.service.ResultService;


@RestController
@RequestMapping("result")
public class ResultTemplateController {
	@Autowired
	private ResultService resultService;

	@GetMapping("template/{id}")
	public TransData getData(@PathVariable("id") String id) {
		return TransFactory.getSuccessResponse(resultService.getTemplate(id));
	}

	@PostMapping
	public TransData getResult(@RequestBody ResultTemplate resultTemplate) {
		resultService.sendResult(resultTemplate);
		return TransFactory.getSuccessResponse();
	}
}
