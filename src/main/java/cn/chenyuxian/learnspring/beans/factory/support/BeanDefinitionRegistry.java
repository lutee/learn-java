package cn.chenyuxian.learnspring.beans.factory.support;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {
	
	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
	
	BeanDefinition getBeanDefinition(String beanName) throws BeansException;
	
	boolean containsBeanDefinition(String beanName);
	
	String[] getBeanDefinitionNames();
}
