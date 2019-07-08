package it_web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.sarah.model.Article;
import com.sarah.service.ArticleService;
import com.sarah.util.PageBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
@Transactional
public class aTest {
	@Autowired
	private ArticleService articleService;
	private final static Logger logger = LoggerFactory.getLogger(aTest.class);
	@Test
	public void test1() {
		System.out.println("1111111111111111111111111");
		 PageBean<Article> u = articleService.seleteByCategory(1, 5, 1);
		//logger.info(JSON.toJSONString(u));
		System.out.println(JSON.toJSONString(u));
	}
}
