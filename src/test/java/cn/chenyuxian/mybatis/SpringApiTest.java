package cn.chenyuxian.mybatis;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;

import cn.chenyuxian.mybatis.dao.ISchoolDao;
import cn.chenyuxian.mybatis.dao.IUserDao;
import cn.chenyuxian.mybatis.pojo.School;
import cn.chenyuxian.mybatis.pojo.User;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration("classpath:spring-config.xml")
@Slf4j
public class SpringApiTest {
	
	@Resource
	private ISchoolDao schoolDao;
	
	@Resource
	private IUserDao userDao;
	
	@Test
	public void test_query() {
		School ruleTree = schoolDao.querySchoolInfoById(1L);
		log.info(JSON.toJSONString(ruleTree));
		
		User user = userDao.queryUserInfoById(1L);
		log.info(JSON.toJSONString(user));
	}
}
