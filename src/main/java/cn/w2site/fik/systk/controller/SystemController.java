package cn.w2site.fik.systk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import cn.w2site.fik.systk.repository.KafkaSender;

@RestController
@RequestMapping("/api/system")
public class SystemController {

	private Logger logger = LoggerFactory.getLogger(SystemController.class);

	@Autowired
	KafkaSender kafkaSender;

	@GetMapping("/kafka/send")
	public String WarnInfo() throws JsonProcessingException {
		int count = 10;
		for (int i = 0; i < count; i++) {
			kafkaSender.sendChannelMess();
			logger.error("KafkaSender start send mess " + i);
		}
		return "success";
	}
}
