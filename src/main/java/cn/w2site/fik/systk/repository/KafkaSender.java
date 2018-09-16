package cn.w2site.fik.systk.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.w2site.fik.systk.entity.Book;

@Component
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	private static final ObjectMapper mapper = new ObjectMapper();

	public void sendChannelMess() throws JsonProcessingException {
		Book book = new Book();
		book.setId("11");
		book.setName("一万个为什么");
		book.setType("幼儿");
		book.setMessage("我爱coding");
		kafkaTemplate.send("book", book.getId(), mapper.writeValueAsString(book));
	}

}
