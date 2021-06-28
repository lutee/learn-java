package cn.chenyuxian.spring.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import cn.chenyuxian.spring.beans.factory.config.SingletonBeanRegistry;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{

	private Map<String, Object> singletonObjects = new HashMap<>();
	
	@Override
	public Object getSingleton(String beanName) {
		return singletonObjects.get(beanName);
	}
	
	protected void addSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject);
	}
	
	

}
