package space.nyuki.qclient.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.nyuki.qclient.factory.TransFactory;
import space.nyuki.qclient.pojo.TransData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
	@RequestMapping("/error")
	public TransData getError(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(200);
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == 404) {
			return TransFactory.getFailedResponse(404, "page not find");
		} else {
			return TransFactory.getFailedResponse(500, "server error");
		}
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
