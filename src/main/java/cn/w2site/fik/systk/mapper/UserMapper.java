package cn.w2site.fik.systk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.w2site.fik.systk.entity.UserEntity;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM t_user")
	@Results({ @Result(property = "id", column = "id"), @Result(property = "name", column = "name"),
			@Result(property = "age", column = "age") })
	List<UserEntity> getAll();

	@Select("SELECT * FROM t_user WHERE id = #{id}")
	UserEntity findOne(int id);

	@Insert("INSERT INTO t_user(id, name, age) VALUES(#{id}, #{name}, #{age})")
	void insert(UserEntity user);

	@Update("UPDATE t_user SET name=#{name},age=#{age} WHERE id =#{id}")
	void update(UserEntity user);

	@Delete("DELETE FROM t_user WHERE id =#{id}")
	void delete(int id);

	List<UserEntity> all();
}
