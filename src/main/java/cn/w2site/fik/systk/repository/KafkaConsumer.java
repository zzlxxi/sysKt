package cn.w2site.fik.systk.repository;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	private Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = { "book" })
	public void receiveMess(ConsumerRecord<String, String> consumerRecord) {
		logger.error(consumerRecord.offset() + "");
		logger.error(consumerRecord.value().toString());
	}
}
