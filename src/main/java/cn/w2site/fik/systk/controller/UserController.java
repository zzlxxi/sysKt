package cn.w2site.fik.systk.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.w2site.fik.systk.entity.UserEntity;
import cn.w2site.fik.systk.entity.UserEntity2;
import cn.w2site.fik.systk.mapper.UserMapper;
import cn.w2site.fik.systk.repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRepository userRepository;

	@ResponseBody
	@GetMapping("findOne")
	public UserEntity findOne(int id) {
		return userMapper.findOne(id);
	}

	@ResponseBody
	@GetMapping("getAll")
	public List<UserEntity> getAll() {
		return userMapper.getAll();
	}

	@ResponseBody
	@GetMapping("all")
	public List<UserEntity> all() {
		List<UserEntity> users = userMapper.all();
		List<UserEntity2> users2 = new ArrayList<>();
		for (UserEntity userEntity : users) {
			UserEntity2 entity2 = new UserEntity2();
			entity2.setAge(userEntity.getAge() + "");
			entity2.setId(userEntity.getId() + "");
			entity2.setName(userEntity.getName());
			users2.add(entity2);
		}
		userRepository.saveAll(users2);
		return users;
	}

	@ResponseBody
	@PostMapping("save")
	public UserEntity save(UserEntity userEntity) {
		userMapper.insert(userEntity);
		return userEntity;
	}
}
