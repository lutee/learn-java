package cn.chenyuxian.spring;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

import cn.chenyuxian.spring.bean.UserDao;
import cn.chenyuxian.spring.bean.UserService;
import cn.chenyuxian.spring.beans.PropertyValue;
import cn.chenyuxian.spring.beans.PropertyValues;
import cn.chenyuxian.spring.beans.factory.config.BeanDefinition;
import cn.chenyuxian.spring.beans.factory.config.BeanReference;
import cn.chenyuxian.spring.beans.factory.support.DefaultListableBeanFactory;
import cn.chenyuxian.spring.beans.factory.xml.XmlBeanDefinitionReader;
import cn.chenyuxian.spring.common.MyBeanFactoryPostProcessor;
import cn.chenyuxian.spring.common.MyBeanPostProcessor;
import cn.chenyuxian.spring.context.support.ClassPathXmlApplicationContext;
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
	
	@Test
	public void test_prototype() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
		applicationContext.registerShutdownHook();
		
		UserService userService = applicationContext.getBean("userService", UserService.class);
		UserService userService2 = applicationContext.getBean("userService", UserService.class);
		
		System.out.println(userService);
		System.out.println(userService2);
		
		System.out.println(userService + "十六进制哈希:" + Integer.toHexString(userService.hashCode()));
		System.out.println(ClassLayout.parseInstance(userService).toPrintable());
	}
}
