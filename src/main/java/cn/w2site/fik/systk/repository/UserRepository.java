package cn.w2site.fik.systk.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import cn.w2site.fik.systk.entity.UserEntity2;

@Repository
public interface UserRepository extends ElasticsearchRepository<UserEntity2, String> {

}
