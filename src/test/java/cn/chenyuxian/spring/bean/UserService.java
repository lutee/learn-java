package cn.chenyuxian.spring.bean;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.factory.BeanClassLoaderAware;
import cn.chenyuxian.learnspring.beans.factory.BeanFactory;
import cn.chenyuxian.learnspring.beans.factory.BeanFactoryAware;
import cn.chenyuxian.learnspring.beans.factory.BeanNameAware;
import cn.chenyuxian.learnspring.beans.factory.DisposableBean;
import cn.chenyuxian.learnspring.beans.factory.InitializingBean;
import cn.chenyuxian.learnspring.context.ApplicationContext;
import cn.chenyuxian.learnspring.context.ApplicationContextAware;

public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware{
	
	private ApplicationContext applicationContext;
	private BeanFactory beanFactory;
	
	private String uid;
	private String company;
	private String location;
	private IUserDao userDao;
	
	public String queryUserInfo() {
		 return userDao.queryUserName(uid) + "," + company + "," + location;
	}
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		this.beanFactory = beanFactory;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext = applicationContext;
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		// TODO Auto-generated method stub
		System.out.println("ClassLoader:" + classLoader);
	}

	@Override
	public void setBeanName(String name) {
		// TODO Auto-generated method stub
		System.out.println("Bean Name is:" + name);
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("执行：UserService.destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("执行：UserService.afterPropertiesSet");
	}
	
	
}
