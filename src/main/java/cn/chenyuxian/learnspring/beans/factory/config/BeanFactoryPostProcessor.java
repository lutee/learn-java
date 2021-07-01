package cn.chenyuxian.learnspring.beans.factory.config;

import cn.chenyuxian.learnspring.beans.BeansException;
import cn.chenyuxian.learnspring.beans.factory.ConfigurableListableBeanFactory;

public interface BeanFactoryPostProcessor {
	
	/**
	 * 在所有的beandefinition加载完成后，实例化bean对象之前，提供修改beandefinition属性的机制
	 * @param beanFactory
	 * @throws BeansException
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
