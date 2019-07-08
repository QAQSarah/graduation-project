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
import com.sarah.model.User;
import com.sarah.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
@Transactional
public class TestSM {
	@Autowired
	private UserService userService;
	private final static Logger logger = LoggerFactory.getLogger(TestSM.class);

	@Test
	public void test1() {
		System.out.println("1111111111111111111111111");
		User u = userService.selectByPrimaryKey(0);
		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-DD HH:mm:ss"));
	}
	@Test
	public void test2() {
		System.out.println("1111111111111111111111111");
		User user=new User();
		String u = userService.insertUser(user);
		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-DD HH:mm:ss"));
	}
}
