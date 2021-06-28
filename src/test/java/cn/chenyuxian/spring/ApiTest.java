package cn.chenyuxian.spring;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import cn.chenyuxian.spring.bean.UserDao;
import cn.chenyuxian.spring.bean.UserService;
import cn.chenyuxian.spring.beans.PropertyValue;
import cn.chenyuxian.spring.beans.PropertyValues;
import cn.chenyuxian.spring.beans.factory.config.BeanDefinition;
import cn.chenyuxian.spring.beans.factory.config.BeanReference;
import cn.chenyuxian.spring.beans.factory.support.DefaultListableBeanFactory;
import cn.chenyuxian.spring.core.io.DefaultResourceLoader;
import cn.chenyuxian.spring.core.io.Resource;
import cn.hutool.core.io.IoUtil;

public class ApiTest {

	private DefaultResourceLoader resourceLoader;
	
	@Before
	public void init() {
		resourceLoader = new DefaultResourceLoader();
	}
	
	@Test
	public void test_classpath() throws IOException {
		Resource resource = resourceLoader.getResource("classpath:important.properties");
		InputStream in = resource.getInputStream();
		String content = IoUtil.readUtf8(in);
		System.out.println(content);
	}
	
	@Test
	public void test_file() throws IOException {
		Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
		InputStream in = resource.getInputStream();
		String content = IoUtil.readUtf8(in);
		System.out.println(content);
	}
	
	@Test
	public void test_url() throws IOException {
		Resource resource = resourceLoader.getResource("https://github.com");
		InputStream in = resource.getInputStream();
		String content = IoUtil.readUtf8(in);
		System.out.println(content);
	}
	
	@Test
	public void test_BeanFactory() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("uid", "10001"));
		propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
		BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
		beanFactory.registerBeanDefinition("userService", beanDefinition);
		UserService userService = (UserService) beanFactory.getBean("userService");
		userService.queryUserInfo();
	}
}