package cn.w2site.fik.systk.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.w2site.fik.systk.entity.UserEntity;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {

	ObjectMapper objectMapper = new ObjectMapper();
	private Logger logger = LoggerFactory.getLogger(UserMapperTest.class);

	@Autowired
	private UserMapper userMapper;

	@Test
	public void save() {
		userMapper.insert(new UserEntity(2, "LI/ZHENGGUANG2", 25));
	}

	@Test
	public void all() throws JsonProcessingException {
		logger.error(objectMapper.writeValueAsString(userMapper.all()).toString());
	}

	@Test
	public void getAll() throws JsonProcessingException {
		logger.error(objectMapper.writeValueAsString(userMapper.getAll()).toString());
	}

}
