package cn.w2site.fik.systk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

	@ResponseBody
	@RequestMapping(value = "/session")
	public Map<String, Object> getSession(HttpServletRequest request) {
		request.getSession().setAttribute("username", "admin");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sessionId", request.getSession().getId());
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/get")
	public String get(HttpServletRequest request) {
		String userName = (String) request.getSession().getAttribute("username");

		return userName;
	}
}
