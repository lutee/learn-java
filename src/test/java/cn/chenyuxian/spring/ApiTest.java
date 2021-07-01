package cn.chenyuxian.spring;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import cn.chenyuxian.learnspring.beans.PropertyValue;
import cn.chenyuxian.learnspring.beans.PropertyValues;
import cn.chenyuxian.learnspring.beans.factory.config.BeanDefinition;
import cn.chenyuxian.learnspring.beans.factory.config.BeanReference;
import cn.chenyuxian.learnspring.beans.factory.support.DefaultListableBeanFactory;
import cn.chenyuxian.learnspring.beans.factory.xml.XmlBeanDefinitionReader;
import cn.chenyuxian.learnspring.context.support.ClassPathXmlApplicationContext;
import cn.chenyuxian.learnspring.core.io.DefaultResourceLoader;
import cn.chenyuxian.learnspring.core.io.Resource;
import cn.chenyuxian.spring.bean.UserDao;
import cn.chenyuxian.spring.bean.UserService;
import cn.chenyuxian.spring.common.MyBeanFactoryPostProcessor;
import cn.chenyuxian.spring.common.MyBeanPostProcessor;
import cn.hutool.core.io.IoUtil;

public class ApiTest {

	private DefaultResourceLoader resourceLoader;
	
	@Before
	public void init() {
		resourceLoader = new DefaultResourceLoader();
	}
	
	@Test
	public void test_classpath() throws IOException {
		resourceLoader = new DefaultResourceLoader();
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
		Resource resource = resourceLoader.getResource("https://github.com/YXCLING/learn-java/blob/master/src/test/resources/important.properties");
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
	
	@Test
	public void test_xml() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions("classpath:spring.xml");
		UserService userService = beanFactory.getBean("userService", UserService.class);
		String result = userService.queryUserInfo();
		System.out.println("测试结果:" + result);
	}
	
	@Test
	public void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		reader.loadBeanDefinitions("classpath:spring.xml");
		MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
		beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
		MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
		beanFactory.addBeanPostProcessor(beanPostProcessor);
		UserService userService = beanFactory.getBean("userService", UserService.class);
		String result = userService.queryUserInfo();
		System.out.println("测试结果:" + result);
	}
	
	@Test
	public void test_xml2() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		UserService userService = applicationContext.getBean("userService", UserService.class);
		String result = userService.queryUserInfo();
		System.out.println("测试结果:" + result);
	}
	
	@Test
	public void test_xml3() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		applicationContext.registerShutdownHook();
		UserService userService = applicationContext.getBean("userService", UserService.class);
		String result = userService.queryUserInfo();
		System.out.println("测试结果:" + result);
	}
	
	@Test
	public void test_xml4() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		applicationContext.registerShutdownHook();
		
		UserService userService = applicationContext.getBean("userService", UserService.class);
		String result = userService.queryUserInfo();
		System.out.println("测试结果:" + result);
		System.out.println("ApplicationContextAware:" + userService.getApplicationContext());
		System.out.println("BeanFactoryAware:" + userService.getBeanFactory());
	}
}
