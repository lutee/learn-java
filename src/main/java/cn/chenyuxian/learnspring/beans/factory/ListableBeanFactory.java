package cn.chenyuxian.learnspring.beans.factory;

import java.util.Map;

import cn.chenyuxian.learnspring.beans.BeansException;

public interface ListableBeanFactory extends BeanFactory{
	
	<T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
	
	String[] getBeanDefinitionNames();
}
