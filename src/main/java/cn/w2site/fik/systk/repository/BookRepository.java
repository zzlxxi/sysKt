package cn.w2site.fik.systk.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import cn.w2site.fik.systk.entity.Book;

@Repository
public interface BookRepository extends ElasticsearchRepository<Book, String> {

}
