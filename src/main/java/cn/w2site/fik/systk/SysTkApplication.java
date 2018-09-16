package cn.w2site.fik.systk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableRedisHttpSession
@MapperScan("cn.w2site.fik.systk.mapper")
public class SysTkApplication {

	public static void main(String[] args) {
		SpringApplication.run(SysTkApplication.class, args);
	}
}
