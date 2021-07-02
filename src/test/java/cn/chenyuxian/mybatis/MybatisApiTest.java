package cn.chenyuxian.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.alibaba.fastjson.JSON;

import cn.chenyuxian.learnmybatis.pojo.User;

public class MybatisApiTest {
	
	@Test
	public void test_queryUserInfoById() {
		String resource = "spring/mybatis-config-datasource.xml";
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session = sqlMapper.openSession();
			try {
				User user = session.selectOne("cn.chenyuxian.learnmybatis.dao.IUserDao.queryUserInfoById", 1L);
				System.out.println(JSON.toJSONString(user));
			} finally {
				session.close();
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
