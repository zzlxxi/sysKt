package cn.w2site.fik.systk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.w2site.fik.systk.entity.Book;
import cn.w2site.fik.systk.repository.BookRepository;

@RestController
@RequestMapping("book")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	private Logger logger = LoggerFactory.getLogger(BookController.class);

	@Async
	@PostMapping("/save")
	public Book save(Book book) {
		bookRepository.save(book);
		return book;
	}

	@GetMapping("/get/{id}")
	public Optional<Book> getById(@PathVariable String id) {
		return bookRepository.findById(id);
	}

	@GetMapping("/all")
	public List<Map<String, Object>> searchAll() {
		Client client = elasticsearchTemplate.getClient();
		SearchRequestBuilder builder = client.prepareSearch("product").setTypes("book");
		SearchResponse response = builder.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
		SearchHits hits = response.getHits();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (SearchHit hit : hits) {
			list.add(hit.getSource());
			logger.debug(hit.getSourceAsString());
		}
		return list;
	}

}
